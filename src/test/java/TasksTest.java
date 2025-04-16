import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TasksTest {

    @Test
    void simpleTaskShouldMatchTitle() {
        SimpleTask task = new SimpleTask(1, "Купить молоко и хлеб");

        assertTrue(task.matches("Куп"));
        assertTrue(task.matches("хле"));
        assertTrue(task.matches("МОЛО"));
        assertTrue(task.matches("Купить"));
        assertTrue(task.matches("хлеб"));
        assertTrue(task.matches("молоко"));

        assertFalse(task.matches("сыр"));
        assertFalse(task.matches(""));
        assertFalse(task.matches(null));
        assertFalse(task.matches("ветчина"));
    }

    @Test
    void simpleTaskWithEmptyTitle() {
        SimpleTask task = new SimpleTask(2, "");
        assertFalse(task.matches(""));
        assertFalse(task.matches("test"));
    }

    @Test
    void simpleTaskWithNullTitle() {
        SimpleTask task = new SimpleTask(3, null);
        assertEquals("", task.getTitle());
    }

    @Test
    void epicShouldMatchAnySubtask() {
        Epic epic = new Epic(3, new String[]{"Молоко", "Яйца", "Хлеб"});

        assertTrue(epic.matches("яйца"));
        assertTrue(epic.matches("ХЛЕБ"));
        assertTrue(epic.matches("оло"));
        assertFalse(epic.matches("Сыр"));
    }

    @Test
    void epicWithEmptySubtasksShouldNotMatch() {
        Epic epic = new Epic(4, new String[]{});
        assertFalse(epic.matches("что-то"));
    }

    @Test
    void epicWithNullSubtasksShouldHandleGracefully() {
        Epic epic = new Epic(5, null);
        assertFalse(epic.matches("тест"));
    }

    @Test
    void epicWithMixedSubtasks() {
        Epic epic = new Epic(6, new String[]{"", "Сыр", null});
        assertFalse(epic.matches(""));
        assertTrue(epic.matches("СЫР"));
        assertFalse(epic.matches(null));
    }

    @Test
    void meetingShouldMatchTopicOrProject() {
        Meeting meeting = new Meeting(6, "Финансы", "Бюджет 2024", "Завтра");

        assertTrue(meeting.matches("фин"));
        assertTrue(meeting.matches("БЮДЖЕТ"));
        assertTrue(meeting.matches("2024"));
        assertFalse(meeting.matches("Завтра"));
    }

    @Test
    void meetingWithEmptyFieldsShouldNotMatch() {
        Meeting meeting = new Meeting(7, "", "", "Сейчас");
        assertFalse(meeting.matches("любой"));
    }

    @Test
    void meetingShouldHandleNullFields() {
        Meeting meeting = new Meeting(8, null, null, null);
        assertFalse(meeting.matches("тест"));
        assertEquals("", meeting.getTopic());
        assertEquals("", meeting.getProject());
    }

    @Test
    void meetingWithNullTopicButProjectMatches() {
        Meeting meeting = new Meeting(9, null, "ПроектX", "Старт");
        assertTrue(meeting.matches("проектx"));
    }

    @Test
    void meetingWithNullProjectButTopicMatches() {
        Meeting meeting = new Meeting(10, "ТемаY", null, "Старт");
        assertTrue(meeting.matches("темаy"));
    }

    @Test
    void taskIdsAreCorrect() {
        SimpleTask simpleTask = new SimpleTask(11, "Заголовок");
        assertEquals(11, simpleTask.getId());

        Epic epic = new Epic(12, new String[]{"Подзадача"});
        assertEquals(12, epic.getId());

        Meeting meeting = new Meeting(13, "Тема", "Проект", "Время");
        assertEquals(13, meeting.getId());
    }

    @Test
    void taskConstructorThrowsForNonPositiveId() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleTask(0, "Заголовок"));
        assertThrows(IllegalArgumentException.class, () -> new Epic(-1, new String[]{"Подзадача"}));
        assertThrows(IllegalArgumentException.class, () -> new Meeting(-2, "Тема", "Проект", "Время"));
    }

    @Test
    void emptyQueryShouldNotMatch() {
        SimpleTask simpleTask = new SimpleTask(14, "Заголовок");
        Epic epic = new Epic(15, new String[]{"Подзадача"});
        Meeting meeting = new Meeting(16, "Тема", "Проект", "Время");

        assertFalse(simpleTask.matches(""));
        assertFalse(epic.matches(""));
        assertFalse(meeting.matches(""));
    }

    @Test
    void caseInsensitivityInMatching() {
        SimpleTask simpleTask = new SimpleTask(17, "Заголовок");
        Epic epic = new Epic(18, new String[]{"Подзадача"});
        Meeting meeting = new Meeting(19, "Тема", "Проект", "Время");

        assertTrue(simpleTask.matches("ЗАГОЛОВОК"));
        assertTrue(epic.matches("ПОДЗАДАЧА"));
        assertTrue(meeting.matches("ТЕМА"));
        assertTrue(meeting.matches("ПРОЕКТ"));
    }
}
