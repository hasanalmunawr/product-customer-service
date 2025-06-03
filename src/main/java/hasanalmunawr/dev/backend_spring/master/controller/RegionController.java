package hasanalmunawr.dev.backend_spring.master.controller;

import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
import hasanalmunawr.dev.backend_spring.master.api.request.BranchRequest;
import hasanalmunawr.dev.backend_spring.master.api.request.RegionRequest;
import hasanalmunawr.dev.backend_spring.master.service.RegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Base.REGION)
@Tag(name = "Region Controller", description = "API for managing region-related operations.")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping()
    public ResponseEntity<?> getAllRegions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return regionService.getAllRegions(page, size, sort, filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegionById(@PathVariable Integer id) {
        return regionService.getRegionById(id);
    }

    @PostMapping(Endpoint.Basic.CREATE)
    public ResponseEntity<?> createRegion(@Valid @RequestBody RegionRequest request) {
        return regionService.createRegion(request);
    }

    @PutMapping(Endpoint.Basic.UPDATE + "/{id}")
    public ResponseEntity<?> updateRegion(@PathVariable Integer id, @Valid @RequestBody RegionRequest request) {
        return regionService.updateRegion(id, request);
    }

    @DeleteMapping(Endpoint.Basic.DELETE + "/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer id) {
        return regionService.deleteRegion(id);
    }

}
