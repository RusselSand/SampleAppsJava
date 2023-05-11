public class Converter {

    public double getDistance(int steps){
        int CM_IN_STEP = 75;
        double CM_IN_KM = 100000.0;
        return steps * CM_IN_STEP / CM_IN_KM;
    }

    public double getCalories(int steps){
        int CAL_IN_STEP = 50;
        double CAL_IN_KCAL = 1000.0;
        return steps * CAL_IN_STEP / CAL_IN_KCAL;
    }
}
