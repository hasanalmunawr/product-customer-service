package hasanalmunawr.dev.backend_spring.master.api.response;

import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RegionResponse {

    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RegionResponse fromModel(Region region) {
        return new RegionResponse()
                .setId(region.getId())
                .setName(region.getName())
                .setCreatedAt(region.getCreatedAt())
                .setUpdatedAt(region.getUpdatedAt());
    }

}
