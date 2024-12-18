package com.coursework.sushibarbackend;

import com.coursework.sushibarbackend.shoppingCart.controller.ShoppingCartController;
import com.coursework.sushibarbackend.shoppingCart.model.dto.CartItemViewDTO;
import com.coursework.sushibarbackend.shoppingCart.service.ShoppingCartService;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    void testAddToCart() throws Exception {
        CartItemViewDTO cartItem = new CartItemViewDTO();
        cartItem.setQuantity(2);

        ApiResponse response = new ApiResponse(true, "Товар добавлен в корзину"){};

        Mockito.when(shoppingCartService.addToCart(Mockito.any(CartItemViewDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/cart/addToCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cartItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Товар добавлен в корзину"));
    }

    @Test
    void testRemoveFromCart() throws Exception {
        ApiResponse response = new ApiResponse(true, "Товар удалён из корзины"){};

        Mockito.when(shoppingCartService.removeFromCart(1)).thenReturn(response);

        mockMvc.perform(delete("/cart/removeFromCart/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Товар удалён из корзины"));
    }

    @Test
    void testIncreaseProductQuantity() throws Exception {
        ApiResponse response = new ApiResponse(true, "Количество увеличено"){};

        Mockito.when(shoppingCartService.increaseProductQuantity(1)).thenReturn(response);

        mockMvc.perform(post("/cart/increaseProductQuantityInCart/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Количество увеличено"));
    }
}

