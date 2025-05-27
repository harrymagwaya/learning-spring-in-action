package com.taco.taco.data.repositories;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.taco.taco.data.Ingredient;
import com.taco.taco.data.Taco;
import com.taco.taco.data.models.TacoRepository;

public class JdbcTacoRepository implements TacoRepository{
    
    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco){
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);

        for (Ingredient ingredient : taco.getIngredients()) {
            save(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = 
        new PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values (?,?)", 
        Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
            Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        // to get the generated  taco id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbc.update("insert into Taco_Ingredients(taco, ingredient)" + "values (?,?", tacoId, ingredient.getId());

    }
}
