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
    public ArrayList<Recipe> readData() throws IOException{

        return new ArrayList<>();
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException{

        return new ArrayList<>();
    }
}
