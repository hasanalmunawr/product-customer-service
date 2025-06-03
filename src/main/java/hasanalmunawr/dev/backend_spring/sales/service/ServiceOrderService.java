package hasanalmunawr.dev.backend_spring.sales.service;

import hasanalmunawr.dev.backend_spring.master.api.request.ProductRequest;
import hasanalmunawr.dev.backend_spring.sales.api.request.ServiceOrderRequest;
import hasanalmunawr.dev.backend_spring.sales.api.response.ServiceOrderResponse;
import org.springframework.http.ResponseEntity;

public interface ServiceOrderService {

    ResponseEntity<?> createServiceOrder(ServiceOrderRequest request);
    ResponseEntity<?> getAllServiceOrders(int page, int size, String sort, String filter);
    ResponseEntity<?> getServiceOrderById(Integer id);
    ResponseEntity<?> updateServiceOrder(Integer id, ServiceOrderRequest request);
    ResponseEntity<?> deleteServiceOrder(Integer id);

}
