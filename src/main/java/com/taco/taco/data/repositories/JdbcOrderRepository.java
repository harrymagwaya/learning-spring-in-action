package com.taco.taco.data.repositories;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.taco.data.Order;
import com.taco.taco.data.Taco;
import com.taco.taco.data.models.OrderRepository;

public class JdbcOrderRepository  implements OrderRepository{

    private SimpleJdbcInsert ordeInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbc){
        this.ordeInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }


    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderid = saveOrderDetails(order);
        order.setId(orderid);

        List<Taco> tacos = order.getTacos();

        for (Taco taco : tacos) {
            saveTacoOrder(taco, orderid);
        }

        return order;
    }
    
    private long saveOrderDetails(Order order){
        @SuppressWarnings("unchecked")
        Map <String, Object> values = objectMapper.convertValue(order, Map.class); // used to convert an obj to a map
        values.put("placedAt", order.getPlacedAt()); // placed at values will be the values

        long orderId = ordeInserter.executeAndReturnKey(values)
                                    .longValue();
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId){
       
        Map <String , Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        ordeInserter.execute(values);
    }
    
}
