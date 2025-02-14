package test.solution.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.solution.market.dto.ProductDto;
import test.solution.market.dto.ProductParamDto;
import test.solution.market.service.ProductService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ProductDto> products = productService.getAllProducts(PageRequest.of(page, size));

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}/params")
    public ResponseEntity<List<ProductParamDto>> getProductParams(@PathVariable Long productId) {
        List<ProductParamDto> params = productService.getProductParams(productId);

        return ResponseEntity.ok(params);
    }
}
