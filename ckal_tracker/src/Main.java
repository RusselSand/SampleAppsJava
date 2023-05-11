import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StepTracker tracker = new StepTracker();
        int userInput = -1;
        do {
            printMenu();
            String input = scanner.next();
            OptionalInt result = tryParseInt(input);
            if(result.isPresent()){
                userInput = result.getAsInt();
                if(userInput < 0 || userInput > 3){
                    System.out.printf("Wrong action. %s does not exist. Put only numbers from 0 to 3%n", input);
                    continue;
                }
                if(userInput == 3){
                    System.out.println("Set new step goal number");
                    input = scanner.next();
                    result = tryParseInt(input);
                    //tracker.changeStepGoal(input);
                }
            }
            else{
                System.out.printf("Wrong action. %s does not exist. Put only numbers from 0 to 3%n", input);
            }
        }
        while(userInput != 0);
        System.out.println("Program finished");
    }

    private static void printMenu(){
        System.out.println("This is calorie tracker! Choose actions from those options:");
        System.out.println("1 — Input steps for specific day");
        System.out.println("2 — Print statistics for specific month");
        System.out.println("3 — Change step goal");
        System.out.println("0 — Exit");
    }
    private static OptionalInt tryParseInt(String input){
        OptionalInt intInput = OptionalInt.empty();
        try{
            intInput = OptionalInt.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
        }
        return intInput;
    }
}