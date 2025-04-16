public class Meeting extends Task {
    protected String topic;
    protected String project;
    protected String start;

    public Meeting(int id, String topic, String project, String start) {
        super(id);
        this.topic = topic != null ? topic : "";
        this.project = project != null ? project : "";
        this.start = start;
    }

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {
        return start;
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        String lowerQuery = query.toLowerCase();
        return topic.toLowerCase().contains(lowerQuery) || project.toLowerCase().contains(lowerQuery);
    }
}
