package hasanalmunawr.dev.backend_spring.master.api.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchRequest {

//    @JsonProperty("branch_name")
    private String branchName;

//    @JsonProperty("unit_replace_location")
    private String unitReplaceLocation;

}
