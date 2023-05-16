public class Task {
    private String name;
    private String description;
    private final int id;
    private int state;
    public static final String[] STATUS = {"NEW", "IN_PROGRESS", "DONE"};

    public Task(String name, String description, int id, int state) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return id + ": " + name + " — " + STATUS[state];
    }
}
