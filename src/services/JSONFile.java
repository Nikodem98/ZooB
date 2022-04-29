package services;

import com.google.gson.*;
import model.animals.Animal;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class JSONFile implements FileActions{

    private String extension = ".json";

    @Override
    public List<Animal> readFile(String fileName) {

        List<Animal> animals = new LinkedList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName)); LineNumberReader r = new LineNumberReader(br)) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Animal.class, new AnimalAdapter());
            Gson gson = new Gson();
            JsonArray ja = gson.fromJson(r, JsonArray.class);

            for (int i = 0; i < ja.size(); i++) {

                JsonObject jsonObject = ja.get(i).getAsJsonObject();

                int current = 0;
                String name = jsonObject.get("name").getAsString();
                String animalType = jsonObject.get("animalType").getAsString();
                String weight = jsonObject.get("weight").getAsString();

                String[] line = {name, weight, animalType};

                Animal animal = createAnimal(line, current);

                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//            List<Animal> animals = gson.fromJson(br, new TypeToken<List<Animal>>(){}.getType());
        return animals;
    }
    @Override
    public void writeFile(String date, List<Animal> animals) {

        String fileName = "zoo_" + date + extension;

        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                throw new Exception("Taki plik juz istnieje.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(animals, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
