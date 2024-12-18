package com.coursework.sushibarbackend;

import com.coursework.sushibarbackend.order.controller.OrderController;
import com.coursework.sushibarbackend.order.model.dto.OrderCreateDTO;
import com.coursework.sushibarbackend.order.model.dto.OrderItemCreateDTO;
import com.coursework.sushibarbackend.order.model.dto.PaymentMethodCompositeDTO;
import com.coursework.sushibarbackend.order.model.dto.ShippingMethodCompositeDTO;
import com.coursework.sushibarbackend.order.service.OrderService;
import com.coursework.sushibarbackend.store.model.dto.StoreNestedDTO;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() throws Exception {
        // Arrange
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setTotalAmount(100.50f);
        orderCreateDTO.setDeliveryAddress("123 Sushi St, Tokyo");

        PaymentMethodCompositeDTO paymentMethod = new PaymentMethodCompositeDTO();
        // Заполните необходимые поля paymentMethod
        orderCreateDTO.setPaymentMethod(paymentMethod);

        ShippingMethodCompositeDTO shippingMethod = new ShippingMethodCompositeDTO();
        // Заполните необходимые поля shippingMethod
        orderCreateDTO.setShippingMethod(shippingMethod);

        OrderItemCreateDTO orderItem = new OrderItemCreateDTO();
        // Заполните необходимые поля orderItem
        orderCreateDTO.setOrderItems(List.of(orderItem));

        StoreNestedDTO store = new StoreNestedDTO();
        // Заполните необходимые поля store
        orderCreateDTO.setStore(store);

        ApiResponse expectedResponse = new ApiResponse(true, "Order created successfully"){};
        when(orderService.create(orderCreateDTO)).thenReturn(expectedResponse);

        // Act
        ApiResponse actualResponse = orderController.create(orderCreateDTO);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.isSuccess(), actualResponse.isSuccess());

        verify(orderService, times(1)).create(orderCreateDTO);
    }
}
