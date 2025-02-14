package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;
import com.recipeapp.model.Recipe;

public interface DataHandler {

    String getMode();

    ArrayList<Recipe> readData() throws IOException;

    void writeData(Recipe recipe) throws IOException;

    ArrayList<Recipe> searchData(String keyword) throws IOException;
}
