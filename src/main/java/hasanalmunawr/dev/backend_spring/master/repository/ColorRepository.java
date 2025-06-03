package hasanalmunawr.dev.backend_spring.master.repository;

import hasanalmunawr.dev.backend_spring.base.repository.BaseRepository;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends BaseRepository<Color> {

    @Query(
            value = """
        SELECT * FROM color 
        WHERE LOWER(colorcode) LIKE LOWER(CONCAT('%', :search, '%')) 
           OR LOWER(colorname) LIKE LOWER(CONCAT('%', :search, '%'))
        """,
            countQuery = """
        SELECT COUNT(*) FROM color 
        WHERE LOWER(colorcode) LIKE LOWER(CONCAT('%', :search, '%')) 
           OR LOWER(colorname) LIKE LOWER(CONCAT('%', :search, '%'))
        """,
            nativeQuery = true
    )
    Page<Color> searchColors(@Param("search") String search, Pageable pageable);


}
