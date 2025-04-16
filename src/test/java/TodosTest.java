import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    void searchShouldFindMatchingTasks() {
        Todos todos = new Todos();
        SimpleTask task = new SimpleTask(1, "Купить Хлеб");
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting meeting = new Meeting(3, "Хлеб", "Проект", "Завтра");

        todos.add(task);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("Хлеб");
        assertEquals(3, result.length);
    }
}
