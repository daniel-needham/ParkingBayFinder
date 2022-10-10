package WebScraper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class FileManager {
    final String filePath = "./data/listOfParkingsBays.json";
    private List<ParkingBay> listOfParkingBays;
    private Gson gson;
    private Reader reader;

    public FileManager() {
        this.listOfParkingBays = null;
        gson = new Gson();

    }

    public void loadListOfParkingBays() throws IOException {
        reader = Files.newBufferedReader(Paths.get(filePath));
        listOfParkingBays = gson.fromJson(reader, new TypeToken<List<ParkingBay>>() {}.getType());
    }

    public void saveListOfParkingBays() throws IOException {
        Writer write = new FileWriter(filePath);
        gson.toJson(listOfParkingBays, write);
        write.close();
    }

    public void startUp(){
        try {
            loadListOfParkingBays();
            System.out.println("success");
        } catch (IOException e) {
            System.out.println("File not found");
            System.out.println("Scraping from internet");
            listOfParkingBays = Scrape.getListOfParkingBays();
        }

        try {
            saveListOfParkingBays();
        } catch( IOException e) {
            System.out.println("Unable to save");
            e.printStackTrace();
        }


    }

    public List<ParkingBay> getListOfParkingBays() {
        return listOfParkingBays;
    }
}
