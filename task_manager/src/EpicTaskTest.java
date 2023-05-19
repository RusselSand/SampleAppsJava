import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EpicTaskTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "1, 1, 1",
            "2, 1, 2",
            "0, 1, 0"
    })
    void checkState(int subTasksSum, int subTasksSize, int result) {
        EpicTask task = new EpicTask("TestName", "TestDescription", Task.Status.NEW);
        task.checkState(subTasksSum, subTasksSize);
        Assertions.assertEquals(result, task.getState().ordinal());
    }
}