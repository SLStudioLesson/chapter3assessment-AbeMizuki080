package com.recipeapp.ui;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    private List<String> getIngredientNames(List<Ingredient> ingredients){
        List<String> ingredientNames = new ArrayList<>();
        for(Ingredient ingredient :ingredients){
            ingredientNames.add(ingredient.getName());
        }
        return ingredientNames;
    }
    
    public void displayMenu() {

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    displayRecipes();
                        break;
                    case "2":
                    addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if (recipes.isEmpty()) {
                System.out.println("No recipes available.");
            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (Recipe recipe : recipes) {
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredients: " + String.join(", ", getIngredientNames(recipe.getIngredients())));
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addNewRecipe(){
        try {
            System.out.println("Adding a new recipe.");

            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            List<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");

            boolean done = false;

            while (!done) {
                System.out.print("ingredients: ");
                String ingredient = reader.readLine();

                if (ingredient.equalsIgnoreCase("done")) {
                    done = true;
                    System.out.println("Finished entering ingredients.");
                }else{
                    ingredients.add(new Ingredient(ingredient));
                }
            }

            ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>(ingredients);

            Recipe newRecipe = new Recipe(recipeName, ingredientsArrayList);

            dataHandler.writeData(newRecipe);

            System.out.println("Recipe added successfully.");

        } catch (IOException e) {

            System.out.println("Failed to add new recipe: " + e.getMessage());
            
        }
    }

}
