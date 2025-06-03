package hasanalmunawr.dev.backend_spring.master.api.request;

import hasanalmunawr.dev.backend_spring.master.api.response.ColorResponse;
import hasanalmunawr.dev.backend_spring.master.api.response.ProductResponse;
import hasanalmunawr.dev.backend_spring.master.model.Color;
import hasanalmunawr.dev.backend_spring.master.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class ProductRequest {

    @NotBlank(message = "Product category must not be blank")
    private String productCategory;

    @NotEmpty(message = "Product must have at least one color")
    private Set<Integer> colorIds;


}
