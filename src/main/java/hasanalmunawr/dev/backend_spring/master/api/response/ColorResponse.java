package hasanalmunawr.dev.backend_spring.master.api.response;

import hasanalmunawr.dev.backend_spring.master.model.Color;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ColorResponse {

    private Integer id;
    private String colorCode;
    private String colorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ColorResponse fromModel(Color color) {
        return new ColorResponse()
                .setId(color.getId())
                .setColorCode(color.getColorCode())
                .setColorName(color.getColorName())
                .setCreatedAt(color.getCreatedAt())
                .setUpdatedAt(color.getUpdatedAt());
    }
}
