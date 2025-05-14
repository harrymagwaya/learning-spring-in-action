package com.taco.taco.data.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.taco.taco.data.Ingredient;
import com.taco.taco.data.models.IngredientRepository;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }


    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    // @Override
    // public Ingredient findOne(String id) {
    //    return jdbc.queryForObject("Select id, name, type from Ingredient where id = ? ",this::mapRowToIngredient, id);
    // }

    @Override
    public Ingredient findOne(String id){
        return jdbc.queryForObject("select id, name, type from ingredient where id = ?",
         new RowMapper<Ingredient>() {
            @Override
            @Nullable
            public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
               return new Ingredient(
                rs.getString("id"), 
                rs.getString("name"), 
                Ingredient.Type.valueOf(rs.getString("type")));
            }
        }, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient){

        jdbc.update("insert into ingredient (id, name, type) values (?,?,?)",  
        ingredient.getId(),
        ingredient.getName(),
        ingredient.getType());

        return ingredient;
    }

    private  Ingredient mapRowToIngredient(ResultSet rs , int row) throws SQLException{

         return new Ingredient(
            rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type"))
         );
    }


   
}
