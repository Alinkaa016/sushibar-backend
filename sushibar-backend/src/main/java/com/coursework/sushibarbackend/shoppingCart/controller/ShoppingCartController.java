package com.coursework.sushibarbackend.shoppingCart.controller;
import com.coursework.sushibarbackend.product.service.ProductService;
import com.coursework.sushibarbackend.shoppingCart.model.dto.CartItemViewDTO;
import com.coursework.sushibarbackend.shoppingCart.service.ShoppingCartService;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.service.AuthService;
import com.coursework.sushibarbackend.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @PostMapping(path="/addToCart")
    public ApiResponse addToCart(@RequestBody CartItemViewDTO cartItemViewDTO) throws Exception {
        return shoppingCartService.addToCart(cartItemViewDTO);
    }

    @DeleteMapping(path="/removeFromCart/{productId}")
    public ApiResponse removeFromCart(@PathVariable int productId) throws Exception {
        return shoppingCartService.removeFromCart(productId);
    }

    @PostMapping(path="/reduceProductQuantityInCart/{productId}")
    public ApiResponse reduceProductQuantityInCart(@PathVariable int productId) throws Exception {
        return shoppingCartService.reduceProductQuantity(productId);
    }

    @PostMapping(path="/increaseProductQuantityInCart/{productId}")
    public ApiResponse increaseProductQuantityInCart(@PathVariable int productId) throws Exception {
        return shoppingCartService.increaseProductQuantity(productId);
    }
}
