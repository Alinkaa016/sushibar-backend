package com.coursework.sushibarbackend.product.service;

import com.coursework.sushibarbackend.product.model.entity.Category;
import com.coursework.sushibarbackend.product.model.entity.Product;
import com.coursework.sushibarbackend.product.repository.CategoryRepository;
import com.coursework.sushibarbackend.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Page<Product> filterByCategory(int categoryId, Pageable pageable){
        return productRepository.findByCategory_Id(categoryId, pageable);
    }

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getById(int productId){
        return productRepository.findById(productId).orElseThrow();
    }

    public void test(){

        try {

            List<Product> products = productRepository.findAll();
            for (int i = 0; i < products.size(); i++){
                Product product = products.get(i);
                String imagePath = "images/sushi.png";
                ClassPathResource imageResource = new ClassPathResource(imagePath);
                Path path = imageResource.getFile().toPath();
                byte[] imageBytes = Files.readAllBytes(path);
                product.setImage(imageBytes);
                productRepository.save(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Product> searchByCategoryAndName(int categoryId, String name, Pageable pageable) {
        return productRepository.findByCategoryAndNameContainingIgnoreCase(categoryId, name, pageable);
    }
}
