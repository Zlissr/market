package test.solution.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.solution.market.dto.OrderItemDto;
import test.solution.market.dto.ProductDto;
import test.solution.market.dto.ProductParamDto;
import test.solution.market.exception.InsufficientQuantityException;
import test.solution.market.exception.ResourceNotFoundException;
import test.solution.market.model.ProductEntity;
import test.solution.market.model.ProductParamEntity;
import test.solution.market.repository.ProductParamRepository;
import test.solution.market.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductParamRepository productParamRepository;

    public List<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductParamDto> getProductParams(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Услуга не найдена, id=" + productId)
                );

        List<ProductParamEntity> paramEntities = productParamRepository.findByProduct(product);

        return paramEntities.stream()
                .map(this::toParamDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void validateAndUpdateStock(List<OrderItemDto> items) {
        for (OrderItemDto item : items) {
            ProductEntity product = productRepository.findById(item.getProductId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                            "Услуга не найдена, id=" + item.getProductId()
                            )
                    );

            if (product.getQuantity() < item.getQuantity()) {
                throw new InsufficientQuantityException(
                        "Нет в наличии товара: " + findById(item.getProductId()).getName()
                );
            }

            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }
    }

    private ProductDto toProductDto(ProductEntity product) {
        return new ProductDto(
                product.getProductId(),
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    private ProductParamDto toParamDto(ProductParamEntity e) {
        return new ProductParamDto(
                e.getParamDictionary().getCode(),
                e.getParamDictionary().getLabel(),
                e.getParamValue()
        );
    }

    public ProductEntity findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Услуга не найдена, id=" + productId)
                );
    }
}
