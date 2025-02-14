package com.recipeapp.model;

import java.util.ArrayList;
import java.util.List;;

public class Recipe {
    private String name;
    private List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

        public String getName(){
            return name;
        }

        public ArrayList<Ingredient> getIngredients(){
            return new ArrayList<>(ingredients);
        }

        public void setName(String name){
            this.name = name;
        }
        public void setIngredients(List<Ingredient> ingredients){
            this.ingredients = ingredients;
        }
    }
    

