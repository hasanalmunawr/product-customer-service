package hasanalmunawr.dev.backend_spring.master.controller;

import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
import hasanalmunawr.dev.backend_spring.master.api.request.BranchRequest;
import hasanalmunawr.dev.backend_spring.master.service.BranchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Base.BRANCH)
@Tag(name = "Branch Controller", description = "API for managing branch-related operations.")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping()
    public ResponseEntity<?> getAllBranch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return branchService.getAllBranches(page, size, sort, filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable Integer id) {
        return branchService.getBranchById(id);
    }

    @PostMapping(Endpoint.Basic.CREATE)
    public ResponseEntity<?> createBranch(@Valid @RequestBody BranchRequest branch) {
        return branchService.createBranch(branch);
    }

    @PutMapping(Endpoint.Basic.UPDATE + "/{id}")
    public ResponseEntity<?> updateBranch(@PathVariable Integer id, @Valid @RequestBody BranchRequest request) {
        return branchService.updateBranch(id, request);
    }

    @DeleteMapping(Endpoint.Basic.DELETE + "/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Integer id) {
        return branchService.deleteBranch(id);
    }


}
