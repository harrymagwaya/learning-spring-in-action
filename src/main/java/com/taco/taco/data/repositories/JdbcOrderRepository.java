package com.taco.taco.data.repositories;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.taco.data.Order;
import com.taco.taco.data.models.OrderRepository;

public class JdbcOrderRepository  implements OrderRepository{

    private SimpleJdbcInsert ordeInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;


    @Override
    public Order save(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
