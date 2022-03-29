package com.sergax.restapi.servlet;

import com.google.gson.Gson;
import com.sergax.restapi.model.Event;
import com.sergax.restapi.model.User;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.EventRepositoryImplementation;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.UserRepositoryImplementation;
import org.hibernate.HibernateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private final UserRepositoryImplementation userRepositoryImplementation =
            new UserRepositoryImplementation();
    private final EventRepositoryImplementation eventRepositoryImplementation =
            new EventRepositoryImplementation();
    private final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print(" User All : ");
        List<User> userList = userRepositoryImplementation.getAll();
        out.print(gson.toJson(userList));
        out.flush();

        out.print(" User by ID : ");
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            User user = userRepositoryImplementation.getById(id);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(gson.toJson(user));
            out.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
            out.print("Your id is wrong, try again");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("New User :");
        String userName = request.getParameter("userName");
        Long eventId = Long.valueOf(request.getParameter("eventId"));
        Event event = eventRepositoryImplementation.getById(eventId);
        User newUser = new User(userName, event);
        userRepositoryImplementation.create(newUser);

        out.print(gson.toJson(newUser));
        out.flush();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("Updated User :");
        Long id = Long.valueOf(request.getParameter("id"));
        String userName = request.getParameter("userName");
        Long eventId = Long.valueOf(request.getParameter("eventId"));
        Event event = eventRepositoryImplementation.getById(eventId);
        User updatedUser = new User(id, userName, event);
        userRepositoryImplementation.update(updatedUser);

        out.print(gson.toJson(updatedUser));
        out.flush();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            userRepositoryImplementation.delete(id);
            out.print("User by ID : " + id + " was deleted ");
        } catch (HibernateException e) {
            e.printStackTrace();
            out.print("Doesn't exist this id ");
        }
    }
}

