package hasanalmunawr.dev.backend_spring.budget.controller;

import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
import hasanalmunawr.dev.backend_spring.budget.api.BudgetRequest;
import hasanalmunawr.dev.backend_spring.budget.service.BudgetService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hasanalmunawr.dev.backend_spring.sales.api.swagger.ServiceOrderExample.SERVICE_ORDER_CREATE_RESPONSE_SUCCESS;
import static hasanalmunawr.dev.backend_spring.sales.api.swagger.ServiceOrderExample.SERVICE_ORDER_REQUEST_JSON;

@RestController
@RequestMapping(Endpoint.Base.BUDGET)
@Tag(name = "Budget Controller", description = "API for managing service-order-related operations.")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping()
    public ResponseEntity<?> getAllBudgets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return budgetService.getAllBudgets(page, size, sort, filter);
    }

    @GetMapping("/mine")
    public ResponseEntity<?> getMyBudgets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return budgetService.getMyBudgets(page, size, sort, filter);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBudgetById(@PathVariable Integer id) {
        return budgetService.getBudgetById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank Account Response Success",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SERVICE_ORDER_CREATE_RESPONSE_SUCCESS))),
    })
    @PostMapping(Endpoint.Basic.CREATE)
    public ResponseEntity<?> createBudget(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Service Order Request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BudgetRequest.class),
                            examples = @ExampleObject(value = SERVICE_ORDER_REQUEST_JSON)
                    )
            )
            @Valid @RequestBody BudgetRequest request
    ) {
        return budgetService.createBudget(request);
    }

    @PutMapping(Endpoint.Basic.UPDATE + "/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable Integer id, @Valid @RequestBody BudgetRequest request) {
        return budgetService.updateBudget(id, request);
    }

    @DeleteMapping(Endpoint.Basic.DELETE + "/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Integer id) {
        return budgetService.deleteBudget(id);
    }

}
