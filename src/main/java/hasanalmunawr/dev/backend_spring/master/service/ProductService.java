package hasanalmunawr.dev.backend_spring.master.service;

import hasanalmunawr.dev.backend_spring.master.api.request.ColorRequest;
import hasanalmunawr.dev.backend_spring.master.api.request.ProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> createProduct(ProductRequest request);
    ResponseEntity<?> getAllProducts(int page, int size, String sort, String filter);
    ResponseEntity<?> getProductById(Integer id);
    ResponseEntity<?> updateProduct(Integer id, ProductRequest request);
    ResponseEntity<?> deleteProduct(Integer id);

}
