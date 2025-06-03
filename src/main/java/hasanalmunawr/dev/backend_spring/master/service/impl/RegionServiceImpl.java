package hasanalmunawr.dev.backend_spring.master.service.impl;

import hasanalmunawr.dev.backend_spring.base.api.PaginationResponse;
import hasanalmunawr.dev.backend_spring.base.constants.ResponseMessage;
import hasanalmunawr.dev.backend_spring.base.exception.NotFoundException;
import hasanalmunawr.dev.backend_spring.base.repository.GeneralRepository;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.master.api.request.RegionRequest;
import hasanalmunawr.dev.backend_spring.master.api.response.BranchResponse;
import hasanalmunawr.dev.backend_spring.master.api.response.RegionResponse;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import hasanalmunawr.dev.backend_spring.master.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final GeneralRepository generalRepository;

    private final TaskProcessor taskProcessor;

    @Override
    public ResponseEntity<?> createRegion(RegionRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Region region = new Region()
                    .setName(request.getName());

            generalRepository.getRegionRepository().save(region);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_CREATED, RegionResponse.fromModel(region));
        });
    }

    @Override
    public ResponseEntity<?> getAllRegions(int page, int size, String sort, String filter) {
        return taskProcessor.executeResponseHttp(() -> {

            Page<Region> regionData = getRegions(page, size, sort, filter);

            List<RegionResponse> regionContent = regionData.getContent()
                    .stream()
                    .map(RegionResponse::fromModel)
                    .toList();

            PaginationResponse response = new PaginationResponse()
                    .setPage(page)
                    .setSize(size)
                    .setTotalPage(regionData.getTotalPages())
                    .setTotalData(regionData.getTotalElements())
                    .setCurrentPage(regionData.getNumber() + 1)
                    .setData(regionContent);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, response);
        });
    }

    @Override
    public ResponseEntity<?> getRegionById(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Region region = generalRepository.getRegionRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, RegionResponse.fromModel(region));
        });
    }

    @Override
    public ResponseEntity<?> updateRegion(Integer id, RegionRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Region region = generalRepository.getRegionRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            region.setName(request.getName());
            generalRepository.getRegionRepository().save(region);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_UPDATED, RegionResponse.fromModel(region));
        });
    }

    @Override
    public ResponseEntity<?> deleteRegion(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {
            Optional<Region> region = generalRepository.getRegionRepository()
                    .findById(id);

            if (!region.isPresent()) throw new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND);

            generalRepository.getRegionRepository().delete(region.get());

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_DELETED, null);
        });
    }

    private Page<Region> getRegions(int page, int size, String sort, String filter) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "created_at")
        );
        return generalRepository.getRegionRepository().searchRegions(filter, pageable);
    }
}
