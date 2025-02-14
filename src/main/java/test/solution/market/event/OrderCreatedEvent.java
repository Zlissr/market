package test.solution.market.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import test.solution.market.dto.OrderDto;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {
    private final OrderDto orderDto;
    private final BigDecimal totalPrice;
}