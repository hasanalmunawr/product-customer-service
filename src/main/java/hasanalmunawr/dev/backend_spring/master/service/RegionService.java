package hasanalmunawr.dev.backend_spring.master.service;


import hasanalmunawr.dev.backend_spring.master.api.request.RegionRequest;
import org.springframework.http.ResponseEntity;

public interface RegionService {

    ResponseEntity<?> createRegion(RegionRequest request);
    ResponseEntity<?> getAllRegions(int page, int size, String sort, String filter);
    ResponseEntity<?> getRegionById(Integer id);
    ResponseEntity<?> updateRegion(Integer id, RegionRequest request);
    ResponseEntity<?> deleteRegion(Integer id);

}
