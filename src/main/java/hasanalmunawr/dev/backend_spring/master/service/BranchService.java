package hasanalmunawr.dev.backend_spring.master.service;

import hasanalmunawr.dev.backend_spring.master.api.request.BranchRequest;
import org.springframework.http.ResponseEntity;

public interface BranchService {

    ResponseEntity<?> createBranch(BranchRequest request);
    ResponseEntity<?> getAllBranches(int page, int size, String sort, String filter);
    ResponseEntity<?> getBranchById(Integer id);
    ResponseEntity<?> updateBranch(Integer id, BranchRequest request);
    ResponseEntity<?> deleteBranch(Integer id);

}
