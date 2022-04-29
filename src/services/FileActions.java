package services;

import model.animals.Animal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FileActions {
    /**
     *
     * @param fileName
     * @return
     */
    List<Animal> readFile(String fileName);

    void writeFile(String fileName, List<Animal> animals);

    default Animal createAnimal(String[] rowData, int currentRow) throws Exception {

        String name = getName(rowData, currentRow);
        double weight = getWeight(rowData, currentRow);
        String type = getType(rowData);

        // INITIALIZING CLASS which have constructor that takes arguments
        Constructor<?> c = getAnimalClass(currentRow, type);
        return (Animal) c.newInstance(name, weight, type);
    }

    private Constructor<?> getAnimalClass(int currentRow, String type) throws NoSuchMethodException {
        Constructor<?> c = null;
        try {
             c = Class.forName("model.animals." + type).getConstructor(String.class, Double.TYPE, String.class);
        } catch (ClassNotFoundException e) {
            System.err.println("W linijce " + currentRow + " podany typ jest bledny");
        }
        return c;
    }

    private String getType(String[] rowData) {
        return getRowValue(rowData, 2);
    }

    private String getRowValue(String[] rowData, int column) {
        return rowData[column].trim();
    }

    private double getWeight(String[] rowData, int currentRow) {
        double weight = 0;
        try {
            weight = Double.parseDouble(getRowValue(rowData, 1));
        } catch (NumberFormatException e) {
            System.err.println("W linijce " + currentRow + " podana waga jest bledna");
        }
        return weight;
    }

    private String getName(String[] rowData, int currentRow) throws Exception {
        String name = getRowValue(rowData, 0);
        if ("".equals(name)) {
            throw new Exception("W linijce " + currentRow + " brak imienia");
        }
        return name;
    }
}
