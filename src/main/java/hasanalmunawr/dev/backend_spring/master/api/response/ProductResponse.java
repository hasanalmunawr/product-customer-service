package hasanalmunawr.dev.backend_spring.master.api.response;

import hasanalmunawr.dev.backend_spring.master.model.Color;
import hasanalmunawr.dev.backend_spring.master.model.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class ProductResponse {

    private Integer id;
    private String productCategory;
    private List<ColorResponse> colors;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponse fromModel(Product product) {
        return new ProductResponse()
                .setId(product.getId())
                .setProductCategory(product.getProductCategory())
                .setColors(product.getColors()
                        .stream()
                        .map(ColorResponse::fromModel)
                        .collect(Collectors.toList())
                )
                .setCreatedAt(product.getCreatedAt())
                .setUpdatedAt(product.getUpdatedAt());
    }
}
