package model.animals;

public class Lion extends Animal {

    private double sleepingTime = 8.0;
    private double eatingTime = 0.5;
    private double walkingTime = 1.0;
    private double weightChange;

    public Lion() {
        super();
    }
    public Lion(String name, double weight, String animalType) {
        super(name, weight, animalType);
    }

    public boolean sleep() {

        return super.action(sleepingTime, "sleeping", weightChange);
    }

    private boolean eat() {

        weightChange = eatingTime/ 3;
        return super.action(eatingTime, "eating", weightChange);
    }

    private boolean walk() {
        weightChange = -walkingTime / 4;
        return super.action(walkingTime, "walking", weightChange);
    }

    @Override
    public void simulate(double duration) {
        super.timeLeft = duration;
        printTitle();
        do {

            if (sleep() | eat() | walk()) {

            } else if (!(sleep() | eat() | walk())) {
                System.out.println("Did nothing");
                break;
            } else {
                break;
            }
        } while (super.timeLeft > 0);
        super.printResults();
    }
}
