package hasanalmunawr.dev.backend_spring.master.model;

import hasanalmunawr.dev.backend_spring.base.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table
@Entity
public class Region extends BaseModel {

    private String name;
}
