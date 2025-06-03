package hasanalmunawr.dev.backend_spring.master.service.impl;

import hasanalmunawr.dev.backend_spring.base.api.PaginationResponse;
import hasanalmunawr.dev.backend_spring.base.constants.ResponseMessage;
import hasanalmunawr.dev.backend_spring.base.exception.NotFoundException;
import hasanalmunawr.dev.backend_spring.base.repository.GeneralRepository;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.master.api.request.BranchRequest;
import hasanalmunawr.dev.backend_spring.master.api.response.BranchResponse;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.service.BranchService;
import hasanalmunawr.dev.backend_spring.user.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final GeneralRepository generalRepository;

    private final TaskProcessor taskProcessor;

    @Override
    public ResponseEntity<?> createBranch(BranchRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Branch branch = new Branch()
                    .setBranchName(request.getBranchName())
                    .setUnitReplaceLocation(request.getUnitReplaceLocation());

            generalRepository.getBranchRepository().save(branch);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_CREATED, BranchResponse.fromModel(branch));
        });
    }

    @Override
    public ResponseEntity<?> getAllBranches(int page, int size, String sort, String filter) {
        return taskProcessor.executeResponseHttp(() -> {

            Page<Branch> branchData = getBranches(page, size, sort, filter);

            List<BranchResponse> branchContent = branchData.getContent()
                    .stream()
                    .map(branch -> new BranchResponse().fromModel(branch))
                    .toList();

            PaginationResponse response = new PaginationResponse()
                    .setPage(page)
                    .setSize(size)
                    .setTotalPage(branchData.getTotalPages())
                    .setTotalData(branchData.getTotalElements())
                    .setCurrentPage(branchData.getNumber() + 1)
                    .setData(branchContent);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, response);
        });
    }

    @Override
    public ResponseEntity<?> getBranchById(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Branch branch = generalRepository.getBranchRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, BranchResponse.fromModel(branch));
        });
    }

    @Override
    public ResponseEntity<?> updateBranch(Integer id, BranchRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Branch branch = generalRepository.getBranchRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            updateBranchFromRequest(branch, request);
            generalRepository.getBranchRepository().save(branch);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_UPDATED, null);
        });
    }

    @Override
    public ResponseEntity<?> deleteBranch(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {
            Optional<Branch> branch = generalRepository.getBranchRepository()
                    .findById(id);

            if (!branch.isPresent()) throw new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND);

            generalRepository.getBranchRepository().delete(branch.get());

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_DELETED, null);
        });
    }

    private void updateBranchFromRequest(Branch branch, BranchRequest request) {
        branch.setBranchName(request.getBranchName());
        branch.setUnitReplaceLocation(request.getUnitReplaceLocation());
    }

    private Page<Branch> getBranches(int page, int size, String sort, String filter) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "created_at")
        );
        return generalRepository.getBranchRepository().searchBranches(filter, pageable);
    }
}
