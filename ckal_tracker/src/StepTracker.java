import java.util.Arrays;
import java.util.HashMap;

public class StepTracker {
    int[][] monthToData = new int[12][30];
    int stepGoal = 10000;
    String[] months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
    Converter converter = new Converter();

    public String inputStepsForDay(int month, int day, int steps){
        try{
            monthToData[month][day] = steps;
            return String.format("Success! %d steps for day %d of %s", steps, day + 1, months[month]);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }

    public void printStatistics(int month){
        int series = 0;
        for (int i = 0; i < 30; i++) {
            System.out.printf("Day %d: %d%n", i + 1, monthToData[month][i]);
            if(monthToData[month][i] >= stepGoal){
                series += 1;
            }
            else{
                series = 0;
            }
        }
        int sum = Arrays.stream(monthToData[month]).sum();
        System.out.printf("Steps in total for %s — %d%n", months[month], sum);
        double average = Arrays.stream(monthToData[month]).average().getAsDouble();
        System.out.println("Average steps per day — " + average);
        System.out.println("Passed distance: " + converter.getDistance(sum));
        System.out.println("Burned calories: " + converter.getCalories(sum));
        System.out.println("Best series — " + series);
    }

    public String changeStepGoal(int stepGoal){
        try{
            this.stepGoal = stepGoal;
            return String.format("Success! New goal — %s", stepGoal);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
}
