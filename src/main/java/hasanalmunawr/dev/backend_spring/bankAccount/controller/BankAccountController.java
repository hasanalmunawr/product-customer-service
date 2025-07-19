package hasanalmunawr.dev.backend_spring.bankAccount.controller;

import hasanalmunawr.dev.backend_spring.bankAccount.api.request.BankAccountRequest;
import hasanalmunawr.dev.backend_spring.bankAccount.service.BankAccountService;
import hasanalmunawr.dev.backend_spring.base.constants.Endpoint;
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
@RequestMapping(Endpoint.Base.BANK_ACCOUNT)
@Tag(name = "Bank Account Controller", description = "API for managing service-order-related operations.")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping()
    public ResponseEntity<?> getAllBankAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return bankAccountService.getAllBankAccounts(page, size, sort, filter);
    }

    @GetMapping("/mine")
    public ResponseEntity<?> getMyBankAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(required = false) String filter
    ) {
        return bankAccountService.getMyBankAccounts(page, size, sort, filter);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBankAccountById(@PathVariable Integer id) {
        return bankAccountService.getBankAccountById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank Account Response Success",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = SERVICE_ORDER_CREATE_RESPONSE_SUCCESS))),
    })
    @PostMapping(Endpoint.Basic.CREATE)
    public ResponseEntity<?> createBankAccount(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Service Order Request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BankAccountRequest.class),
                            examples = @ExampleObject(value = SERVICE_ORDER_REQUEST_JSON)
                    )
            )
            @Valid @RequestBody BankAccountRequest request
    ) {
        return bankAccountService.createBankAccount(request);
    }

    @PutMapping(Endpoint.Basic.UPDATE + "/{id}")
    public ResponseEntity<?> updateBankAccount(@PathVariable Integer id, @Valid @RequestBody BankAccountRequest request) {
        return bankAccountService.updateBankAccount(id, request);
    }

    @DeleteMapping(Endpoint.Basic.DELETE + "/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Integer id) {
        return bankAccountService.deleteBankAccount(id);
    }
}
