package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.config.URLConstants;
import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderItemDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderItemDtoList()
                .stream()
                .map(this:: mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItemsList);

        //get the skucode list
        List<String> skuCodeList = order.getOrderLineItemList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        skuCodeList.forEach(System.out::println);
        //call the inventory sevice when placeorder if the product is in stock or not

        InventoryResponse[] inventoryResponses = restTemplate.getForObject(UriComponentsBuilder
                .fromUriString(URLConstants.INVENTORYENDPOINTS.getEndPointURL())
                .queryParam("skuCode",skuCodeList)
                .build()
                .toUriString(),InventoryResponse[].class);

        Arrays.stream(inventoryResponses).forEach(System.out::println);

        Boolean allProductInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);


        if(allProductInStock){
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is out of stock, please try later.");
        }
        return "Order place successful.";
    }

    private OrderLineItems mapToDto(OrderItemDto orderItemDto) {
        OrderLineItems  orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderItemDto.getQuantity());
        orderLineItems.setPrice(orderItemDto.getPrice());
        orderLineItems.setSkuCode(orderItemDto.getSkuCode());
        return orderLineItems;
    }
}
