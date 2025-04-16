import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TasksTest {
    @Test
    void simpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        assertTrue(task.matches("родителям"));
        assertFalse(task.matches("другу"));
    }

    @Test
    void epicMatches() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        assertTrue(epic.matches("Яйца"));
        assertFalse(epic.matches("Хлеб"));
    }

    @Test
    void meetingMatches() {
        Meeting meeting = new Meeting(3, "Выкатка", "НетоБанк", "Завтра");
        assertTrue(meeting.matches("НетоБанк"));
        assertTrue(meeting.matches("Выкатка"));
        assertFalse(meeting.matches("Сегодня"));
    }
}