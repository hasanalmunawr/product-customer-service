package hasanalmunawr.dev.backend_spring.base.repository;

import hasanalmunawr.dev.backend_spring.master.repository.BranchRepository;
import hasanalmunawr.dev.backend_spring.master.repository.ColorRepository;
import hasanalmunawr.dev.backend_spring.master.repository.ProductRepository;
import hasanalmunawr.dev.backend_spring.master.repository.RegionRepository;
import hasanalmunawr.dev.backend_spring.sales.repository.ServiceOrderRepository;
import hasanalmunawr.dev.backend_spring.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GeneralRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;
}
