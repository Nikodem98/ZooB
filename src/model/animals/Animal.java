package model.animals;

import com.google.gson.annotations.Expose;
import lombok.Getter;

public abstract class Animal {

    @Getter @Expose private String name;
    @Getter @Expose protected double weight;
    @Getter @Expose private String animalType;
    protected double oldWeight;
    protected double timeLeft;

    protected Animal() {
    }

    public Animal(String name, double weight, String animalType) {
        this.name = name;
        this.animalType = animalType;
        this.weight = weight;
        this.oldWeight= weight;
    }

    protected void printTitle() {
        System.out.println("\033[1m------" + this.animalType + " " + this.name + "------\033[0m");
    }

    protected void printResults() {
        System.out.println("\033[1mResults:\033[0m");
        if (weight != oldWeight){
            System.out.println("Weight before (" + oldWeight + ") and after (" +  weight +") completing all actions");
        } else {
            System.out.println("Weight (" + weight + ") didn't change");
        }
        System.out.println("Time left: " + timeLeft);
    }

    private void print(double time, String activity) {
        System.out.println("is " + activity + " " + time + " hours");
    }

    protected boolean action(double actionTime, String actionName, double weightLose) {
        if (timeLeft < actionTime){
            return false;
        } else {
            timeLeft -= actionTime;
            weight += weightLose;
            weight = Math.round(weight * 100.0) / 100.0;
            weightLose = Math.round(weightLose * 100.0) / 100.0;
            print(actionTime, actionName);
            if (weightLose < 0) {
                System.out.println("lost: " + -weightLose);
            } else if (weightLose > 0){
                System.out.println("gained: " + weightLose);
            }
            return true;
        }
    }
    public abstract void simulate(double duration);

    public String toString() {
        return  "{ \"name\": \""+name+"\", \"weight\": \""+weight+"\", \"animalType\": \""+animalType+"\" }";
    }
}
