package hasanalmunawr.dev.backend_spring.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hasanalmunawr.dev.backend_spring.bankAccount.model.BankAccount;
import hasanalmunawr.dev.backend_spring.base.model.BaseModel;
import hasanalmunawr.dev.backend_spring.budget.model.BudgetModel;
import hasanalmunawr.dev.backend_spring.category.model.CategoryModel;
import hasanalmunawr.dev.backend_spring.debts.model.DebtModel;
import hasanalmunawr.dev.backend_spring.web.model.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
//@ToString(exclude = "bankAccounts")
@Table(name = "users")
@Entity
public class UserModel extends BaseModel implements UserDetails {

    private String username;
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CategoryModel> categories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<BudgetModel> budgets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<DebtModel> debts;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
