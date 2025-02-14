package com.recipeapp.datahandler;

import java.io.*;
import java.util.ArrayList;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String getMode(){
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
    
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String recipeName = parts[0].trim();
                
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                
                for (int i = 1; i < parts.length; i++) {
                    String ingredientName = parts[i].trim();
                    ingredients.add(new Ingredient(ingredientName));  
                }

                recipes.add(new Recipe(recipeName, ingredients)); 
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + e.getMessage());
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(recipe.getName());

            for (Ingredient ingredient : recipe.getIngredients()) {
                writer.write("," + ingredient.getName());  
            }

            writer.newLine();
        } catch (IOException e) {
            
            throw new IOException("Failed to write data to CSV file", e);
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException{

        return null;
    }
}
