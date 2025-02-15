package test.solution.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.solution.market.dto.OrderDto;
import test.solution.market.dto.OrderItemDto;
import test.solution.market.event.OrderCreatedEvent;
import test.solution.market.model.ProductEntity;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;
    private final ProductService productService;

    @Transactional
    public void placeOrder(OrderDto order) {
        productService.validateAndUpdateStock(order.getItems());
        BigDecimal totalPrice = calculateTotalPrice(order.getItems());

        eventPublisher.publishEvent(
                new OrderCreatedEvent(
                        order,
                        totalPrice
                )
        );
    }

    private BigDecimal calculateTotalPrice(List<OrderItemDto> items) {
        return items.stream()
                .map(item -> {
                    ProductEntity product = productService.findById(item.getProductId());

                    return BigDecimal.valueOf(product.getPrice())
                            .multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}