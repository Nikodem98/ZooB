package services;

import lombok.Getter;
import model.animals.Animal;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class CSVFile implements FileActions {

    private static final String EXTENTION = ".txt";

    @Override
    public List<Animal> readFile(String fileName) {

        List<Animal> animals = new LinkedList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII); LineNumberReader r = new LineNumberReader(br)) {

            r.readLine();
            String line = r.readLine();

            while (line != null) {

                int current = r.getLineNumber() - 1;
                String[] attributes = line.split(",");

                Animal animal = createAnimal(attributes, current);

                animals.add(animal);

                line = r.readLine();
            }
        } catch (Exception e) {
            // TODO Bład czytanie linijki
            e.printStackTrace();
        }

        return animals;
    }

    @Override
    public void writeFile(String date, List<Animal> animals) {

        String fileName = "zoo_" + date + EXTENTION;

        File myObj = new File(fileName);

        try (FileWriter write = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(write);) {

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Taki plik juz istnieje.");
            }

            bw.write("Name, Weight, Type");
            bw.newLine();
            animals.forEach(animal -> {

                try {
                    bw.write(animal.getName() + ", " + animal.getWeight() + ", " + animal.getAnimalType());
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
