public class Epic extends Task {
    protected String[] subtasks;

    public Epic(int id, String[] subtasks) {
        super(id);
        this.subtasks = subtasks != null ? subtasks : new String[0];
    }

    public String[] getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        String lowerQuery = query.toLowerCase();
        for (String subtask : subtasks) {
            if (subtask != null && subtask.toLowerCase().contains(lowerQuery)) {
                return true;
            }
        }
        return false;
    }
}