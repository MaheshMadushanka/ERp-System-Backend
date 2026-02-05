package com.erp.erp_system.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.erp.erp_system.dto.OrderItemDTO;
import com.erp.erp_system.dto.OrderRequestDTO;
import com.erp.erp_system.entity.OrderEntity;
import com.erp.erp_system.entity.OrderItemEntity;
import com.erp.erp_system.entity.ProductEntity;
import com.erp.erp_system.exception.BadRequestException;
import com.erp.erp_system.repository.OrderRepository;
import com.erp.erp_system.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final ModelMapper modelMapper;

    @Override
    public OrderEntity createOrder(OrderRequestDTO request) {

        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(request.getTotalAmount());
        order.setPaymentMethod(request.getPaymentMethod());

        List<OrderItemEntity> itemEntities = new ArrayList<>();

        for (OrderItemDTO dto : request.getItems()) {

            ProductEntity product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() ->
                            new BadRequestException("Product not found"));

            if (product.getQuantity() < dto.getQuantity()) {
                throw new BadRequestException(
                        "Not enough stock for " + product.getName());
            }

            product.setQuantity(
                    product.getQuantity() - dto.getQuantity());
            productRepo.save(product);

            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());

            itemEntities.add(item);
        }

        order.setItems(itemEntities);
        orderRepo.save(order);
        return order;
    }


    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderRequestDTO> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderRequestDTO.class))
                .toList();
    }
}