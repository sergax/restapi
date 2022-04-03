package com.sergax.restapi.servlet;


import com.google.gson.Gson;
import com.sergax.restapi.model.File;
import com.sergax.restapi.repository.hibernateRepositoryImplementation.FileRepositoryImplementation;
import lombok.SneakyThrows;
import org.hibernate.HibernateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/file")
public class FileServlet extends HttpServlet {
    private final FileRepositoryImplementation fileRepositoryImplementation =
            new FileRepositoryImplementation();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print("File All : ");
        List<File> fileList = fileRepositoryImplementation.getAll();

        out.print(gson.toJson(fileList));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/HTML; charset=UTF-8");


        String fileName = request.getParameter("fileName");
        File newFile = new File(fileName);
        fileRepositoryImplementation.create(newFile);

        out.println("File New : ");
        out.print(gson.toJson(newFile));
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Long id = Long.valueOf(request.getParameter("id"));
        String fileName = request.getParameter("fileName");
        File updatedFile = new File(id, fileName);
        fileRepositoryImplementation.update(updatedFile);

        out.print(gson.toJson(updatedFile));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

