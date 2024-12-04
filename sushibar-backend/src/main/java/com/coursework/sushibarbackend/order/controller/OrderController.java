package com.coursework.sushibarbackend.order.controller;

import com.coursework.sushibarbackend.order.model.Database.Order;
import com.coursework.sushibarbackend.order.model.dto.OrderCreateDTO;
import com.coursework.sushibarbackend.order.model.dto.OrderViewDTO;
import com.coursework.sushibarbackend.order.model.dto.PaymentMethodCompositeDTO;
import com.coursework.sushibarbackend.order.model.dto.ShippingMethodCompositeDTO;
import com.coursework.sushibarbackend.order.repository.PaymentMethodRepository;
import com.coursework.sushibarbackend.order.repository.ShippingMethodRepository;
import com.coursework.sushibarbackend.order.service.OrderService;
import com.coursework.sushibarbackend.product.service.ProductService;
import com.coursework.sushibarbackend.store.model.dto.StoreNestedDTO;
import com.coursework.sushibarbackend.store.service.StoreService;
import com.coursework.sushibarbackend.user.model.dto.ApiResponse;
import com.coursework.sushibarbackend.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private ShippingMethodRepository shippingMethodRepository;
    @Autowired
    private StoreService storeService;

    @GetMapping(path = "/getPaymentMethods")
    public List<PaymentMethodCompositeDTO> getPaymentMethods() {
        return paymentMethodRepository.findAll().stream().map(PaymentMethodCompositeDTO::new).toList();
    }

    @GetMapping(path = "/getShippingMethods")
    public List<ShippingMethodCompositeDTO> getShippingMethods() {
        return shippingMethodRepository.findAll().stream().map(ShippingMethodCompositeDTO::new).toList();
    }

    @GetMapping(path = "/getStore")
    public List<StoreNestedDTO> getStore() throws Exception {
        return storeService.getByCart().stream().map(StoreNestedDTO::new).toList();
    }

    @GetMapping(path = "/byUser")
    public List<OrderViewDTO> getByUsername(@RequestParam String username) {
        List<Order> orders = orderService.getByUsername(username);
        return orders.stream().map(OrderViewDTO::new).toList();
    }

    @PostMapping(path = "/create")
    public ApiResponse create(@RequestBody OrderCreateDTO orderCreateDTO) throws Exception {
        return orderService.create(orderCreateDTO);
    }
}
