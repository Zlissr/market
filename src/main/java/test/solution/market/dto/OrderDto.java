package test.solution.market.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String clientName;
    private String clientEmail;
    private String deliveryAddress;
    private List<OrderItemDto> items;
}
