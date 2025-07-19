package hasanalmunawr.dev.backend_spring.base.repository;


import hasanalmunawr.dev.backend_spring.bankAccount.repository.BankAccountRepository;
import hasanalmunawr.dev.backend_spring.budget.repository.BudgetRepository;
import hasanalmunawr.dev.backend_spring.category.repository.CategoryRepository;
import hasanalmunawr.dev.backend_spring.debts.repository.DebtRepository;
import hasanalmunawr.dev.backend_spring.sales.repository.ServiceOrderRepository;
import hasanalmunawr.dev.backend_spring.user.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GeneralRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private DebtRepository debtRepository;
}
