package com.ecommerce.order.service;

import com.ecommerce.order.client.CartClient;
import com.ecommerce.order.client.PaymentClient;
import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.config.KafkaProducer;
import com.ecommerce.order.dto.response.CartItem;
import com.ecommerce.order.dto.response.CartResponse;
import com.ecommerce.order.dto.response.PaymentResponse;
import com.ecommerce.order.dto.response.ProductResponse;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.enums.PaymentStatus;
import com.ecommerce.order.repository.OrderItemRepository;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartClient cartClient;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private ProductClient productClient;
    private KafkaProducer kafkaProducer;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public String placeOrder(Long userId){
        CartResponse cart = cartClient.getCart(userId);

        if(cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty !");
        }
        BigDecimal total = BigDecimal.ZERO;
        Order order = new Order();
        order.setUserId(userId);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.save(order);

        for(CartItem item : cart.getItems()){
            ProductResponse product = productClient.getProduct(item.getProductId());
            if(product == null){
                throw new RuntimeException("Product not found");
            }
            BigDecimal productMultiply = product.getPrice().multiply(item.getQuantity());
            total  = total.add(productMultiply);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(product.getId());
            orderItem.setName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(product.getStock());

            orderItemRepository.save(orderItem);
        }
        order.setTotalAmount(total.doubleValue());
        orderRepository.save(order);
        PaymentResponse payment = paymentClient.pay(order.getId(), total.doubleValue());
        if(payment.getStatus().equals(PaymentStatus.FAILED)){
            order.setStatus(OrderStatus.FAILED);
            orderRepository.save(order);
            throw new RuntimeException("Payment failed");
        }

        kafkaProducer.sendOrderEvent(order);
        cartClient.clearCart(userId);
        return "Order placed successfully";
    }
}
