import java.util.HashMap;

public class StepTracker {
    HashMap<String, int[]> monthToData = new HashMap<>();
    int stepGoal = 10000;
    String[] months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
    public StepTracker(){
        for (String month: months
             ) {
            int[] days = new int[30];
            monthToData.put(month, days);
        }
    }

    public String inputStepsForDay(int month, int day, int steps){
        return null;
    }

    public String[] printStatistics(int month){
        return null;
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
