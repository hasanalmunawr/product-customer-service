package hasanalmunawr.dev.backend_spring.master.repository;


import hasanalmunawr.dev.backend_spring.base.repository.BaseRepository;
import hasanalmunawr.dev.backend_spring.master.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends BaseRepository<Branch> {


    @Query(
            value = """
        SELECT * FROM branch 
        WHERE LOWER(branchname) LIKE LOWER(CONCAT('%', :search, '%')) 
           OR LOWER(unitreplacelocation) LIKE LOWER(CONCAT('%', :search, '%'))
        """,
            countQuery = """
        SELECT COUNT(*) FROM branch 
        WHERE LOWER(branchname) LIKE LOWER(CONCAT('%', :search, '%')) 
           OR LOWER(unitreplacelocation) LIKE LOWER(CONCAT('%', :search, '%'))
        """,
            nativeQuery = true
    )
    Page<Branch> searchBranches(@Param("search") String search, Pageable pageable);

}
