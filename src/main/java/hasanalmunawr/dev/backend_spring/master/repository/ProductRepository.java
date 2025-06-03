package hasanalmunawr.dev.backend_spring.master.repository;

import hasanalmunawr.dev.backend_spring.base.repository.BaseRepository;
import hasanalmunawr.dev.backend_spring.master.model.Product;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    @Query(
            value = """
        SELECT * FROM product 
        WHERE LOWER(productcategory) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            countQuery = """
        SELECT COUNT(*) FROM product 
        WHERE LOWER(productcategory) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            nativeQuery = true
    )
    Page<Product> searchProducts(@Param("search") String search, Pageable pageable);


}
