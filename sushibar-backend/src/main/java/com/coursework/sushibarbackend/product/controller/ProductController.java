package com.coursework.sushibarbackend.product.controller;

import com.coursework.sushibarbackend.product.model.dto.CategoryCompositeDTO;
import com.coursework.sushibarbackend.product.model.dto.ProductViewDTO;
import com.coursework.sushibarbackend.product.service.ProductService;
import com.coursework.sushibarbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/sortByPrice/{categoryId}")
    public Page<ProductViewDTO> filterByCategory(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productService.filterByCategory(categoryId, pageable).map(ProductViewDTO::new);
    }

    @GetMapping(path = "/byCategory/{categoryId}")
    public Page<ProductViewDTO> filterByCategory(@PathVariable int categoryId,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.filterByCategory(categoryId, pageable).map(ProductViewDTO::new);
    }

    @GetMapping(path = "/categories")
    public List<CategoryCompositeDTO> getCategories() {
        return productService.getCategories().stream().map(CategoryCompositeDTO::new).toList();
    }

    @GetMapping(path = "/search/{categoryId}")
    public Page<ProductViewDTO> search(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return productService.searchByCategoryAndName(categoryId, name, pageable).map(ProductViewDTO::new);
    }

    @GetMapping(path = "/get/{productId}")
    public ProductViewDTO getById(@PathVariable int productId) {
        return new ProductViewDTO(productService.getById(productId));
    }

    @GetMapping(path = "/test")
    public void test() {
        productService.test();
    }
}
