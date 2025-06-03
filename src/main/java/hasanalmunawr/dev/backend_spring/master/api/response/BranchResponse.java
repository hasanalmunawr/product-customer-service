package hasanalmunawr.dev.backend_spring.master.api.response;


import hasanalmunawr.dev.backend_spring.master.model.Branch;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BranchResponse {

    private Integer id;
    private String branchName;
    private String unitReplaceLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BranchResponse fromModel(Branch branch) {
        return new BranchResponse()
                .setId(branch.getId())
                .setBranchName(branch.getBranchName())
                .setUnitReplaceLocation(branch.getUnitReplaceLocation())
                .setCreatedAt(branch.getCreatedAt())
                .setUpdatedAt(branch.getUpdatedAt());
    }
}
