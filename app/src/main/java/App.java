import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            DataHandler dataHandler = null;

            if(choice.equals("1")){
                dataHandler = new CSVDataHandler();
                System.out.println("Current mode: CSV");
            }else if (choice.equals("2")) {
                dataHandler = new JSONDataHandler();
                System.out.println("Current moed: JSON");
            }else {
                System.out.println("Current mode: CSV");
                return;
            }

            RecipeUI recipeUI =new RecipeUI(dataHandler);
            recipeUI.displayMenu();
            
            if(dataHandler != null){
                System.out.println("Data handler selected: " + dataHandler.getClass().getSimpleName());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}