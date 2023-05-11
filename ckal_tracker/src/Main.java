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
                    getStepGoal(scanner, tracker);
                } else if (userInput == 1) {
                    getSteps(scanner, tracker);
                } else if (userInput == 2) {
                    getStatistics(scanner, tracker);
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
    private static void getStepGoal(Scanner scanner, StepTracker tracker){
        int steps = setSteps(scanner, "Set new step goal number");
        String result = tracker.changeStepGoal(steps);
        System.out.println(result);
    }
    private static void getSteps(Scanner scanner, StepTracker tracker){
        int month = chooseMonth(scanner, tracker.months);
        int day = chooseDay(scanner);
        int steps = setSteps(scanner, "Set steps number");
        String result = tracker.inputStepsForDay(month, day, steps);
        System.out.println(result);
    }
    private static void getStatistics(Scanner scanner, StepTracker tracker){
        int month = chooseMonth(scanner, tracker.months);
        tracker.printStatistics(month);
    }
    private static int chooseMonth(Scanner scanner, String[] months){
        OptionalInt result = OptionalInt.empty();
        while(result.isEmpty()) {
            System.out.println("Choose month: ");
            for (int i = 0; i < months.length; i++) {
                System.out.printf("%d — %s %n", i + 1, months[i]);
            }
            String input = scanner.next();
            result = tryParseInt(input);
            if (result.isPresent() && (result.getAsInt() < 1 || result.getAsInt() > 12)) {
                System.out.println("Wrong month. Try again, please");
                result = OptionalInt.empty();
            }
        }
        return result.getAsInt() - 1;
    }
    private static int chooseDay(Scanner scanner){
        OptionalInt result = OptionalInt.empty();
        while(result.isEmpty()) {
            System.out.println("Choose day from 1 to 30: ");
            String input = scanner.next();
            result = tryParseInt(input);
            if (result.isPresent() && (result.getAsInt() < 1 || result.getAsInt() > 30)) {
                System.out.println("Wrong day. Try again, please");
                result = OptionalInt.empty();
            }
        }
        return result.getAsInt() - 1;
    }
    private static int setSteps(Scanner scanner, String message){
        OptionalInt result = OptionalInt.empty();
        while(result.isEmpty()){
            System.out.println(message);
            String input = scanner.next();
            result = tryParseInt(input);
        }
        return result.getAsInt();
    }
}