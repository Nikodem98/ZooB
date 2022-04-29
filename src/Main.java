import model.animals.Animal;
import services.CSVFile;
import services.FileActions;
import services.JSONFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
//        String str = "zoo1.json";
        String str = "zoo.txt";
        FileActions fileActions = getFileActions(str);

        List<Animal> animals = fileActions.readFile(str);
        Double duration = readUserInput();

        for (Animal animal : animals) {
            animal.simulate(duration);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        fileActions.writeFile(dtf.format(now), animals);
    }

    private static Double readUserInput() {
        Scanner scan = new Scanner(System.in);
        Double duration = null;

        do {
            try {
                System.out.println("Podaj czas trwania: ");
                String input = scan.nextLine();
                input = input.replace(",", ".");
                duration = Double.valueOf(input);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Niepoprawna wartosć. Podaj liczbe");
            }

        } while (duration == null);
        return duration;
    }

    private static FileActions getFileActions(String str) throws Exception {
        FileActions ob;

        if (str.contains(".json")) {
            ob = new JSONFile();
        } else if (str.contains(".txt")){
            ob = new CSVFile();
        } else {
            throw new Exception("Złe rozszerzenie pliku");
        }
        return ob;
    }
}
