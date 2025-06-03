package hasanalmunawr.dev.backend_spring.master.controller;

import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
import hasanalmunawr.dev.backend_spring.master.api.request.ColorRequest;
import hasanalmunawr.dev.backend_spring.master.api.request.RegionRequest;
import hasanalmunawr.dev.backend_spring.master.service.ColorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Base.COLOR)
@Tag(name = "Color Controller", description = "API for managing color-related operations.")
public class ColorController {

    @Autowired
    private ColorService colorService;


    @GetMapping()
    public ResponseEntity<?> getAllColors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return colorService.getAllColors(page, size, sort, filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Integer id) {
        return colorService.getColorById(id);
    }

    @PostMapping(Endpoint.Basic.CREATE)
    public ResponseEntity<?> createColor(@Valid @RequestBody ColorRequest request) {
        return colorService.createColor(request);
    }

    @PutMapping(Endpoint.Basic.UPDATE + "/{id}")
    public ResponseEntity<?> updateColor(@PathVariable Integer id, @Valid @RequestBody ColorRequest request) {
        return colorService.updateColor(id, request);
    }

    @DeleteMapping(Endpoint.Basic.DELETE + "/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Integer id) {
        return colorService.deleteColor(id);
    }


}
