package test.solution.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.solution.market.model.ProductEntity;
import test.solution.market.model.ProductParamEntity;

import java.util.List;

public interface ProductParamRepository extends JpaRepository<ProductParamEntity, Long> {

    List<ProductParamEntity> findByProduct(ProductEntity product);
}
