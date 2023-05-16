public class SubTask extends Task{
    private final int EpicId;

    public SubTask(String name, String description, int id, int state, int epicId) {
        super(name, description, id, state);
        EpicId = epicId;
    }

    public int getEpicId() {
        return EpicId;
    }

}
