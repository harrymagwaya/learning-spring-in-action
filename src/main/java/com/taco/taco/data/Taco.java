package com.taco.taco.data;

import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 1, message = "You should choose atleast 1 ingredient")
    private List<String> ingredients;

    @NotNull
    private long id;

    private Date createdAt;
    

}
