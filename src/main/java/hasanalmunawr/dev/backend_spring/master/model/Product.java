package hasanalmunawr.dev.backend_spring.master.model;

import hasanalmunawr.dev.backend_spring.base.model.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table
@Entity
public class Product extends BaseModel {

    private String productCategory;

    @ManyToMany
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private List<Color> colors = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "product_color",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "color_id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "color_id"})
//    )
//    private Set<Color> colors = new HashSet<>();


}
