package com.github.apska.webapp.web;

import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.storage.IStorage;
import com.github.apska.webapp.storage.XmlFileStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by APS2
 * on 01.08.2016
 */
public class ResumeServlet extends HttpServlet {
    public static XmlFileStorage storage = new XmlFileStorage("C:\\WEB_APP_JAVA\\JavaWebApp\\file_storage");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer w = response.getWriter();
        String name = request.getParameter("name");
        w.write("Тест сервлет: привет " + name);
        w.close();*/

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r;

        switch (action){
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = Resume.EMPTY;
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Действие " + action + " не допустимо.");
        }

        request.setAttribute("resume", r);
        request.getRequestDispatcher(("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")).forward(request, response);
    }
}
