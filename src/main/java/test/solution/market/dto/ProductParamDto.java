package test.solution.market.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductParamDto {
    private String code;
    private String label;
    private String value;
}
