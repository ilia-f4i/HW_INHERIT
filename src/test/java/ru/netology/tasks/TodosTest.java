package ru.netology.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[] {"Молоко", "Яйца", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка версии", "Приложение", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchShouldReturnOnlyMatchingTasks() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить маме"));
        todos.add(new Epic(2, new String[]{"Купить молоко", "Купить хлеб"}));
        todos.add(new Meeting(3, "Обсуждение релиза", "Проект Х", "Понедельник"));

        Task[] actual = todos.search("молоко");
        assertEquals(1, actual.length);
        assertEquals(2, actual[0].getId());
    }
    @Test
    public void shouldAddTasksAndReturnAll() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        Epic epic = new Epic(2, new String[] {"Купить молоко", "Купить хлеб"});
        Meeting meeting = new Meeting(3, "Созвон по проекту", "Банковское приложение", "Пятница");

        Todos todos = new Todos();
        todos.add(task);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {task, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasksByQuery_MatchOne() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить маме"));
        todos.add(new Epic(2, new String[] {"Сходить в магазин", "Купить молоко"}));
        todos.add(new Meeting(3, "Обсуждение релиза", "Проект Х", "Понедельник"));

        Task[] result = todos.search("молоко");

        assertEquals(1, result.length);
        assertEquals(2, result[0].getId());
    }

    @Test
    public void shouldFindTasksByQuery_MatchMultiple() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить маме"));
        todos.add(new Epic(2, new String[] {"Купить молоко", "Купить хлеб"}));
        todos.add(new Meeting(3, "Обсуждение молоко", "Проект Х", "Понедельник")); // 👈 исправлено

        Task[] result = todos.search("молоко");

        assertEquals(2, result.length);
    }
    @Test
    public void shouldReturnEmptyIfNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить маме"));
        todos.add(new Epic(2, new String[] {"Сходить в магазин"}));
        todos.add(new Meeting(3, "Совещание", "Проект Х", "Понедельник"));

        Task[] result = todos.search("отпуск");

        assertEquals(0, result.length);
    }
}
