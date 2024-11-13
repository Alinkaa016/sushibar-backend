package com.coursework.sushibarbackend.store.service;

import com.coursework.sushibarbackend.exception.CustomExceptions.OutOfStockException;
import com.coursework.sushibarbackend.shoppingCart.model.entity.ShoppingCart;
import com.coursework.sushibarbackend.store.model.entity.Store;
import com.coursework.sushibarbackend.store.model.entity.StoreItem;
import com.coursework.sushibarbackend.store.repository.StoreRepository;
import com.coursework.sushibarbackend.user.model.entity.User;
import com.coursework.sushibarbackend.user.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    public List<Store> getByCart() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());
        List<Store> storeList = storeRepository.findAll();
        ShoppingCart cart = user.getShoppingCart();
        List<Set<Store>> storeSets = cart.getCartItems().stream()
                .map(item -> item.getProduct().getStoreList().stream()
                        .map(StoreItem::getStore)
                        .collect(Collectors.toSet()))
                .toList();
        if (storeSets.isEmpty()) {
            throw new OutOfStockException("Все товары в корзине отсутствуют в наличии.");
        }

        Set<Store> commonStores = new HashSet<>(storeSets.get(0));
        storeSets.forEach(commonStores::retainAll);

        if (commonStores.isEmpty()){
            throw new OutOfStockException("Не найден магазин, способный предоставить все выбранные товары.");
        }

        return new ArrayList<>(commonStores);
    }
}