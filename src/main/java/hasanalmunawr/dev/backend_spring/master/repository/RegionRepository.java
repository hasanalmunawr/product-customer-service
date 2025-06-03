package hasanalmunawr.dev.backend_spring.master.repository;

import hasanalmunawr.dev.backend_spring.base.repository.BaseRepository;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import hasanalmunawr.dev.backend_spring.master.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends BaseRepository<Region> {

    @Query(
            value = """
        SELECT * FROM region 
        WHERE LOWER(name) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            countQuery = """
        SELECT COUNT(*) FROM region 
        WHERE LOWER(name) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            nativeQuery = true
    )
    Page<Region> searchRegions(@Param("search") String search, Pageable pageable);

}
