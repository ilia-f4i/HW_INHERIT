package ru.netology.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {

    @Test
    public void simpleTaskMatchesTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        assertTrue(task.matches("маме"));
    }

    @Test
    public void epicMatchesSubtask() {
        Epic epic = new Epic(2, new String[] {"Купить молоко", "Сходить в аптеку"});
        assertTrue(epic.matches("молоко"));
    }

    @Test
    public void meetingMatchesTopicAndProject() {
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект Х", "Завтра");
        assertTrue(meeting.matches("релиза"));
        assertTrue(meeting.matches("Проект"));
    }

    @Test
    public void taskDoesNotMatchQuery() {
        SimpleTask task = new SimpleTask(4, "Сходить в магазин");
        assertFalse(task.matches("аптека"));
    }
    @Test
    public void simpleTaskMatchesQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        assertTrue(task.matches("маме"));
    }

    @Test
    public void simpleTaskDoesNotMatchWrongQuery() {
        SimpleTask task = new SimpleTask(2, "Позвонить маме");
        assertFalse(task.matches("папе"));
    }

    @Test
    public void epicMatchesQueryInSubtasks() {
        Epic epic = new Epic(3, new String[] {"Купить хлеб", "Сходить в аптеку"});
        assertTrue(epic.matches("аптеку"));
    }

    @Test
    public void epicDoesNotMatchQueryNotInSubtasks() {
        Epic epic = new Epic(4, new String[] {"Купить хлеб", "Сходить в аптеку"});
        assertFalse(epic.matches("библиотека"));
    }

    @Test
    public void meetingMatchesQueryInTopic() {
        Meeting meeting = new Meeting(5, "Обсуждение релиза", "Приложение", "Завтра в 10");
        assertTrue(meeting.matches("релиза"));
    }

    @Test
    public void meetingMatchesQueryInProject() {
        Meeting meeting = new Meeting(6, "Обсуждение релиза", "Приложение", "Завтра в 10");
        assertTrue(meeting.matches("Приложение"));
    }

    @Test
    public void meetingDoesNotMatchWrongQuery() {
        Meeting meeting = new Meeting(7, "Обсуждение релиза", "Приложение", "Завтра в 10");
        assertFalse(meeting.matches("отпуск"));
    }
}

