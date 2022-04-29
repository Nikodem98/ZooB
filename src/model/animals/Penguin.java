package model.animals;

public class Penguin extends Animal {

    private double sleepingTime;
    private double eatingTime;
    private double walkingTime;
    private double swimmingTime;
    private double weightChange;

    public Penguin() {
        super();
    }
    public Penguin(String name, double weight, String animalType) {
        super(name, weight, animalType);
        notLazy();
    }


    private void lazy() {
        sleepingTime = 6.0;
        eatingTime = 1.0;
        walkingTime = 1.0;
        swimmingTime = 1.5;
    }

    private void notLazy() {
        sleepingTime = 4.0;
        eatingTime = 1.0;
        walkingTime = 2.0;
        swimmingTime = 2.5;
    }

    public boolean sleep() {
        return super.action(sleepingTime, "sleeping", weightChange);
    }

    private boolean eat() {

        weightChange = eatingTime / 3;
        return super.action(eatingTime, "eating", weightChange);
    }

    private boolean walk() {

        weightChange = -walkingTime / 5;
        return super.action(walkingTime, "walking", weightChange);
    }

    private boolean swim() {

        weightChange = -swimmingTime / 3;
        return super.action(swimmingTime, "swimming", weightChange);
    }
    @Override
    public void simulate(double duration) {
        timeLeft = duration;
        printTitle();
        do {

            if (sleep() | eat() | walk() | swim()) {

            } else if (!(sleep() | eat() | walk() | swim())) {
                System.out.println("Did nothing");
                break;
            } else {
                break;
            }
        } while (timeLeft > 0);
        printResults();
    }


}
