package com.coursework.sushibarbackend;

import com.coursework.sushibarbackend.product.controller.ProductController;
import com.coursework.sushibarbackend.product.model.dto.CategoryCompositeDTO;
import com.coursework.sushibarbackend.product.model.dto.ProductViewDTO;
import com.coursework.sushibarbackend.product.model.entity.Category;
import com.coursework.sushibarbackend.product.model.entity.Product;
import com.coursework.sushibarbackend.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private Product createProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Sushi");
        product.setPrice(10.0f);
        product.setDescription("Delicious sushi");
        product.setRating(4.5f);
        product.setImage(new byte[]{1, 2, 3});

        Category category = new Category();
        category.setId(1);
        category.setName("Japanese Food");
        category.setDescription("Category for Japanese food");
        product.setCategory(category);
        return product;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterByCategoryWithSorting() {
        // Arrange
        int categoryId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Product product = createProduct();
        Page<Product> productPage = new PageImpl<>(List.of(product));
        when(productService.filterByCategory(eq(categoryId), any(Pageable.class))).thenReturn(productPage);

        // Act
        Page<ProductViewDTO> result = productController.filterByCategory(categoryId, 0, 10, "price", "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Sushi", result.getContent().get(0).getName());
        verify(productService, times(1)).filterByCategory(eq(categoryId), any(Pageable.class));
    }

    @Test
    void testFilterByCategory() {
        // Arrange
        int categoryId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Product product = createProduct();
        Page<Product> productPage = new PageImpl<>(List.of(product));
        when(productService.filterByCategory(eq(categoryId), any(Pageable.class))).thenReturn(productPage);

        // Act
        Page<ProductViewDTO> result = productController.filterByCategory(categoryId, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Sushi", result.getContent().get(0).getName());
        verify(productService, times(1)).filterByCategory(eq(categoryId), any(Pageable.class));
    }

    @Test
    void testGetCategories() {
        // Arrange
        Category category = new Category();
        category.setId(1);
        category.setName("Category 1");
        when(productService.getCategories()).thenReturn(List.of(category));

        // Act
        List<CategoryCompositeDTO> result = productController.getCategories();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Category 1", result.get(0).getName());
        verify(productService, times(1)).getCategories();
    }

    @Test
    void testSearch() {
        // Arrange
        int categoryId = 1;
        String name = "Sushi";
        Pageable pageable = PageRequest.of(0, 10);
        Product product = createProduct();
        Page<Product> productPage = new PageImpl<>(List.of(product));
        when(productService.searchByCategoryAndName(eq(categoryId), eq(name), any(Pageable.class))).thenReturn(productPage);

        // Act
        Page<ProductViewDTO> result = productController.search(categoryId, name, 0, 10, "price", "asc");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Sushi", result.getContent().get(0).getName());
        assertEquals("Japanese Food", result.getContent().get(0).getCategory().getName());
        verify(productService, times(1)).searchByCategoryAndName(eq(categoryId), eq(name), any(Pageable.class));
    }

    @Test
    void testGetById() {
        // Arrange
        int productId = 1;
        Product product = createProduct();
        when(productService.getById(productId)).thenReturn(product);

        // Act
        ProductViewDTO result = productController.getById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Sushi", result.getName());
        assertEquals("Japanese Food", result.getCategory().getName());
        verify(productService, times(1)).getById(productId);
    }

    @Test
    void testTest() {
        // Act
        productController.test();

        // Assert
        verify(productService, times(1)).test();
    }
}

