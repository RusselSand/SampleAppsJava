import java.time.Duration;
import java.time.LocalDateTime;

public class EpicTask extends Task{

    public EpicTask(String name, String description, Status state) {
        super(name, description, state);
    }

    public EpicTask(String name, String description, int id, Status state) {
        super(name, description, id, state);
    }

    public EpicTask(String name, String description, Status state, Duration duration, LocalDateTime startTime) {
        super(name, description, state, duration, startTime);
    }

    public void checkState(int subTasksSum, int subTasksSize){
        int epicState;
        if(subTasksSum == 0 ){
            epicState = 0;
        } else if (subTasksSum == subTasksSize * 2) {
            epicState = 2;
        } else{
            epicState = 1;
        }
        this.setState(Task.Status.values()[epicState]);
    }


}
