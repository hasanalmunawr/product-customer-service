package hasanalmunawr.dev.backend_spring.master.service.impl;

import hasanalmunawr.dev.backend_spring.base.api.PaginationResponse;
import hasanalmunawr.dev.backend_spring.base.constants.ResponseMessage;
import hasanalmunawr.dev.backend_spring.base.exception.CustomApiException;
import hasanalmunawr.dev.backend_spring.base.exception.NotFoundException;
import hasanalmunawr.dev.backend_spring.base.repository.GeneralRepository;
import hasanalmunawr.dev.backend_spring.base.task.TaskProcessor;
import hasanalmunawr.dev.backend_spring.master.api.request.ColorRequest;
import hasanalmunawr.dev.backend_spring.master.api.request.ProductRequest;
import hasanalmunawr.dev.backend_spring.master.api.response.ColorResponse;
import hasanalmunawr.dev.backend_spring.master.api.response.ProductResponse;
import hasanalmunawr.dev.backend_spring.master.model.Color;
import hasanalmunawr.dev.backend_spring.master.model.Product;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import hasanalmunawr.dev.backend_spring.master.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final GeneralRepository generalRepository;

    private final TaskProcessor taskProcessor;

    @Override
    public ResponseEntity<?> createProduct(ProductRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            List<Color> colors = new ArrayList<>(generalRepository.getColorRepository().findAllById(request.getColorIds()));

            if (colors.size() != request.getColorIds().size()) {
                throw new CustomApiException("One or more colors not found", HttpStatus.BAD_REQUEST);
            }

            Product product = new Product()
                    .setProductCategory(request.getProductCategory())
                    .setColors(colors);

            generalRepository.getProductRepository().save(product);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_CREATED, ProductResponse.fromModel(product));
        });
    }

    @Override
    public ResponseEntity<?> getAllProducts(int page, int size, String sort, String filter) {
        return taskProcessor.executeResponseHttp(() -> {

            Page<Product> productData = getProducts(page, size, sort, filter);

            List<ProductResponse> productContent = productData.getContent()
                    .stream()
                    .map(ProductResponse::fromModel)
                    .toList();

            PaginationResponse response = new PaginationResponse()
                    .setPage(page)
                    .setSize(size)
                    .setTotalPage(productData.getTotalPages())
                    .setTotalData(productData.getTotalElements())
                    .setCurrentPage(productData.getNumber() + 1)
                    .setData(productContent);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, response);
        });
    }

    @Override
    public ResponseEntity<?> getProductById(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Product product = generalRepository.getProductRepository().findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_FOUND, ProductResponse.fromModel(product));
        });
    }

    @Override
    public ResponseEntity<?> updateProduct(Integer id, ProductRequest request) {
        return taskProcessor.executeResponseHttp(() -> {

            Product product = generalRepository.getProductRepository().findById(id)
                    .orElseThrow(() -> new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND));

            updateProductFromRequest(product, request);
            generalRepository.getProductRepository().save(product);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_UPDATED, null);
        });

    }

    @Override
    public ResponseEntity<?> deleteProduct(Integer id) {
        return taskProcessor.executeResponseHttp(() -> {

            Optional<Product> product = generalRepository.getProductRepository().findById(id);

            if (!product.isPresent()) throw new NotFoundException(ResponseMessage.Resource.RESOURCE_NOT_FOUND);

            generalRepository.getProductRepository().deleteById(id);

            return taskProcessor.success(ResponseMessage.Resource.RESOURCE_DELETED, null);
        });
    }

    private void updateProductFromRequest(Product product, ProductRequest request) {
        List<Color> colors = new ArrayList<>(generalRepository.getColorRepository().findAllById(request.getColorIds()));

        product.setProductCategory(request.getProductCategory());
        product.setColors(colors);
    }

    private Page<Product> getProducts(int page, int size, String sort, String filter) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "created_at")
        );
        return generalRepository.getProductRepository().searchProducts(filter, pageable);
    }
}
