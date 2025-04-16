import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodosTest {
    private Todos todos;

    @BeforeEach
    void setUp() {
        todos = new Todos();
    }

    @Test
    void shouldAddDifferentTaskTypes() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить Хлеб");
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting meeting = new Meeting(3, "Выкатка", "Проект Хлеб", "Завтра");

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        assertArrayEquals(expected, todos.findAll());
    }

    @Test
    void shouldHandleEmptyTaskList() {
        Task[] result = todos.search("Хлеб");
        assertEquals(0, result.length);
    }

    @Test
    void shouldFindMatchingTasksCaseInsensitive() {
        todos.add(new SimpleTask(1, "Хлеб"));
        todos.add(new Epic(2, new String[]{"ХЛЕБ"}));
        todos.add(new Meeting(3, "хлеб", "ПРОЕКТ", "Завтра"));

        Task[] result = todos.search("ХлЕб");
        assertEquals(3, result.length);
    }

    @Test
    void shouldFindByTitleAndSubtasksAndProject() {
        todos.add(new SimpleTask(1, "Купить Хлеб"));
        todos.add(new Epic(2, new String[]{"Молоко", "Хлеб"}));
        todos.add(new Meeting(3, "Выкатка", "Проект Хлеб", "Завтра"));

        Task[] result = todos.search("Хлеб");
        assertEquals(3, result.length);
    }

    @Test
    void shouldNotFindNonMatchingTasks() {
        todos.add(new SimpleTask(4, "Позвонить"));
        todos.add(new Epic(5, new String[]{"Задача"}));
        todos.add(new Meeting(6, "Встреча", "Проект", "Завтра"));

        Task[] result = todos.search("Хлеб");
        assertEquals(0, result.length);
    }

    @Test
    void shouldHandleDuplicateIds() {
        Task task1 = new SimpleTask(1, "Задача 1");
        Task task2 = new Epic(1, new String[]{"Подзадача"});

        todos.add(task1);
        todos.add(task2);

        assertEquals(2, todos.findAll().length);
        assertNotEquals(task1, task2);
    }

    @Test
    void shouldMatchPartialQueries() {
        todos.add(new SimpleTask(7, "Хлебный магазин"));
        Task[] result = todos.search("Хлеб");
        assertEquals(1, result.length);
    }

    @Test
    void shouldHandleEmptyQuery() {
        todos.add(new SimpleTask(1, "Тест"));
        Task[] result = todos.search("");
        assertEquals(0, result.length);
    }
}
