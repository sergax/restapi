package com.sergax.restapi.servlet;

import com.google.gson.Gson;
import com.sergax.restapi.model.Event;
import com.sergax.restapi.model.File;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.EventRepositoryImplementation;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.FileRepositoryImplementation;
import org.hibernate.HibernateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EventServlet", urlPatterns = "/event")
public class EventServlet extends HttpServlet {
    private final EventRepositoryImplementation eventRepositoryImplementation =
            new EventRepositoryImplementation();
    private final FileRepositoryImplementation fileRepositoryImplementation =
            new FileRepositoryImplementation();
    private final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("Event All :");
        List<Event> eventList = eventRepositoryImplementation.getAll();

        out.print(gson.toJson(eventList));
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("New Event : ");
        String eventName = request.getParameter("eventName");
        Long fileId = Long.valueOf(request.getParameter("fileId"));
        File file = fileRepositoryImplementation.getById(fileId);
        Event newEvent = new Event(eventName, file);
        eventRepositoryImplementation.create(newEvent);

        out.print(gson.toJson(newEvent));
        out.flush();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("Updated Event : ");
        Long id = Long.valueOf(request.getParameter("id"));
        String eventName = request.getParameter("eventName");
        Long fileId = Long.valueOf(request.getParameter("fileId"));
        File file = fileRepositoryImplementation.getById(fileId);
        Event updatedEvent = new Event(id, eventName, file);
        eventRepositoryImplementation.update(updatedEvent);

        out.print(gson.toJson(updatedEvent));
        out.flush();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            eventRepositoryImplementation.delete(id);
            out.print("Event by ID : " + id + " was deleted");
        } catch (HibernateException e) {
            e.printStackTrace();
            out.print("Doesn't exist this id ");
        }
    }
}

