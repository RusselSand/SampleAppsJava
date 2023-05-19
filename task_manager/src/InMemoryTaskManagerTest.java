import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    static TaskManager manager;
    @BeforeAll
    static void beforeAll(){
        manager = Managers.getDefault();
    }
    @Test
    void create() {
        final Task task = new Task("Test Create", "Test Description",1, Task.Status.NEW);
        final Task taskResult = manager.create(task);
        assertNotNull(taskResult);
        assertEquals(task, taskResult);
    }

    @Test
    void update() {
        final Task task = new Task("Test Create1", "Test Description", 1, Task.Status.NEW);
        manager.update(task, 1);
        final Task taskResult = manager.getById(1);
        assertNotNull(taskResult);
        assertEquals(task, taskResult);
    }

    @Test
    void delete() {
        manager.delete("Task", 1);
        final Task taskResult = manager.getById(1);
        assertNull(taskResult);
    }
}