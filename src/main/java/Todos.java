public class Todos {
    private Task[] tasks = new Task[0];

    private Task[] addToArray(Task[] current, Task task) {
        Task[] tmp = new Task[current.length + 1];
        System.arraycopy(current, 0, tmp, 0, current.length);
        tmp[tmp.length - 1] = task;
        return tmp;
    }

    public void add(Task task) {
        tasks = addToArray(tasks, task);
    }

    public Task[] findAll() {
        return tasks;
    }

    public Task[] search(String query) {
        if (query == null || query.isEmpty()) {
            return new Task[0];
        }
        String lowerQuery = query.toLowerCase();
        Task[] result = new Task[0];
        for (Task task : tasks) {
            if (task.matches(lowerQuery)) {
                result = addToArray(result, task);
            }
        }
        return result;
    }
}