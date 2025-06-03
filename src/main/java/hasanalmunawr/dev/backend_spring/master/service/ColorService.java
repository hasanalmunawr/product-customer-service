package hasanalmunawr.dev.backend_spring.master.service;

import hasanalmunawr.dev.backend_spring.master.api.request.ColorRequest;
import org.springframework.http.ResponseEntity;

public interface ColorService {

    ResponseEntity<?> createColor(ColorRequest request);
    ResponseEntity<?> getAllColors(int page, int size, String sort, String filter);
    ResponseEntity<?> getColorById(Integer id);
    ResponseEntity<?> updateColor(Integer id, ColorRequest request);
    ResponseEntity<?> deleteColor(Integer id);

}
