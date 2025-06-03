package hasanalmunawr.dev.backend_spring.master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hasanalmunawr.dev.backend_spring.base.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table
@Entity
public class Color extends BaseModel {

    private String colorCode;
    private String colorName;

    @ManyToMany(mappedBy = "colors")
    private Set<Product> products = new HashSet<>();
}
