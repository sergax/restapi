package com.sergax.restapi.servlet;


import com.google.gson.Gson;
import com.sergax.restapi.model.File;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.FileRepositoryImplementation;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/file")
public class FileServlet extends HttpServlet {
    private final FileRepositoryImplementation fileRepositoryImplementation =
            new FileRepositoryImplementation();
    private final Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/all" -> showAll(request, response);
            case "/insert" -> insertFile(request, response);
            case "/delete" -> deleteFile(request, response);
            case "/update" -> updateFile(request, response);
            default -> showAll(request, response);
        }
    }

    protected void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("File All : ");
        List<File> fileList = fileRepositoryImplementation.getAll();

        out.print(gson.toJson(fileList));
        out.flush();
    }

    protected void insertFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("File New : ");
        String fileName = request.getParameter("fileName");
        File newFile = new File(fileName);
        fileRepositoryImplementation.create(newFile);

        out.print(gson.toJson(newFile));
        out.flush();
    }

    protected void updateFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("File Updated : ");
        Long id = Long.valueOf(request.getParameter("id"));
        String fileName = request.getParameter("fileName");
        File updatedFile = new File(id, fileName);
        fileRepositoryImplementation.update(updatedFile);

        out.print(gson.toJson(updatedFile));
        out.flush();
    }

    protected void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            fileRepositoryImplementation.delete(id);
            out.print("File by ID : " + id + " was deleted");
        } catch (HibernateException e) {
            e.printStackTrace();
            out.print("Can't delete this File");
        }
    }
}

