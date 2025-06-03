package hasanalmunawr.dev.backend_spring.master.service.impl;

import hasanalmunawr.dev.backend_spring.base.api.PaginationResponse;
import hasanalmunawr.dev.backend_spring.base.constants.ResponseMessage;
import hasanalmunawr.dev.backend_spring.base.exception.NotFoundException;
import hasanalmunawr.dev.backend_spring.base.repository.GeneralRepository;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.master.api.request.BranchRequest;
import hasanalmunawr.dev.backend_spring.master.api.request.ColorRequest;
import hasanalmunawr.dev.backend_spring.master.api.response.BranchResponse;
import hasanalmunawr.dev.backend_spring.master.api.response.ColorResponse;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.model.Color;
import hasanalmunawr.dev.backend_spring.master.service.ColorService;
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
public class ColorServiceImpl implements ColorService {

    private final GeneralRepository generalRepository;

    private final TaskProcessor taskProcessor;

    @Override
    public ResponseEntity<?> createColor(ColorRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Color color = new Color()
                    .setColorCode(request.getColorCode())
                    .setColorName(request.getColorName());

            generalRepository.getColorRepository().save(color);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_CREATED, ColorResponse.fromModel(color));
        });
    }

    @Override
    public ResponseEntity<?> getAllColors(int page, int size, String sort, String filter) {
        return taskProcessor.executeResponseHttp(() -> {

            Page<Color> colorData = getColors(page, size, sort, filter);

            List<ColorResponse> colorContent = colorData.getContent().stream()
                    .map(ColorResponse::fromModel)
                    .toList();

            PaginationResponse response = new PaginationResponse()
                    .setPage(page)
                    .setSize(size)
                    .setTotalPage(colorData.getTotalPages())
                    .setTotalData(colorData.getTotalElements())
                    .setCurrentPage(colorData.getNumber() + 1)
                    .setData(colorContent);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, response);
        });
    }

    @Override
    public ResponseEntity<?> getColorById(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Color color = generalRepository.getColorRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, ColorResponse.fromModel(color));
        });
    }

    @Override
    public ResponseEntity<?> updateColor(Integer id, ColorRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Color color = generalRepository.getColorRepository()
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            updateColorFromRequest(color, request);
            generalRepository.getColorRepository().save(color);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_UPDATED, null);
        });
    }

    @Override
    public ResponseEntity<?> deleteColor(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Optional<Color> color = generalRepository.getColorRepository().findById(id);

            if (!color.isPresent()) throw new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND);

            generalRepository.getColorRepository().deleteById(id);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_DELETED, null);
        });
    }

    private void updateColorFromRequest(Color color, ColorRequest request) {
        color.setColorCode(request.getColorCode());
        color.setColorName(request.getColorName());
    }

    private Page<Color> getColors(int page, int size, String sort, String filter) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "created_at")
        );
        return generalRepository.getColorRepository().searchColors(filter, pageable);
    }
}
