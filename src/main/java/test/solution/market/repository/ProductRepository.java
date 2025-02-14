package test.solution.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.solution.market.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
