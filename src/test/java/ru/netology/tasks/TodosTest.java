package ru.netology.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
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
        Task task1 = new SimpleTask(1, "Позвонить маме");
        Task task2 = new Epic(2, new String[]{"Купить молоко", "Купить хлеб"});
        Task task3 = new Meeting(3, "Обсуждение релиза", "Проект Х", "Понедельник");
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] actual = todos.search("молоко");
        Task[] expected = {task2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTasksAndReturnAll() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Купить хлеб"});
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
        Task task1 = new SimpleTask(1, "Позвонить маме");
        Task task2 = new Epic(2, new String[]{"Сходить в магазин", "Купить молоко"});
        Task task3 = new Meeting(3, "Обсуждение релиза", "Проект Х", "Понедельник");
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] result = todos.search("молоко");
        Task[] expected = {task2};
        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldFindTasksByQuery_MatchMultiple() {
        Todos todos = new Todos();
        Task task1 = new SimpleTask(1, "Позвонить маме");
        Task task2 = new Epic(2, new String[]{"Купить молоко", "Купить хлеб"});
        Task task3 = new Meeting(3, "Обсуждение молоко", "Проект Х", "Понедельник");
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] result = todos.search("молоко");
        Task[] expected = {task2, task3}; // Порядок зависит от добавления
        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldReturnEmptyIfNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить маме"));
        todos.add(new Epic(2, new String[]{"Сходить в магазин"}));
        todos.add(new Meeting(3, "Совещание", "Проект Х", "Понедельник"));

        Task[] result = todos.search("отпуск");
        Task[] expected = {};
        assertArrayEquals(expected, result);
    }
}
