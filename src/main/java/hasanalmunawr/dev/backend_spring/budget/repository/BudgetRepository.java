package hasanalmunawr.dev.backend_spring.budget.repository;

import hasanalmunawr.dev.backend_spring.base.repository.BaseRepository;
import hasanalmunawr.dev.backend_spring.budget.model.BudgetModel;
import hasanalmunawr.dev.backend_spring.category.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends BaseRepository<BudgetModel> {

    @Query(
            value = """
        SELECT * FROM budgets 
        WHERE LOWER(description) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            countQuery = """
         SELECT * FROM budgets 
        WHERE LOWER(description) LIKE LOWER(CONCAT('%', :search, '%')) 
        """,
            nativeQuery = true
    )
    Page<BudgetModel> searchBudget(@Param("search") String search, Pageable pageable);

    @Query(
            value = "SELECT * FROM budgets WHERE user_id = :userId",
            countQuery = "SELECT count(*) FROM budgets WHERE user_id = :userId",
            nativeQuery = true
    )
    Page<BudgetModel> findByUserId(@Param("userId") Integer userId, Pageable pageable);

}
