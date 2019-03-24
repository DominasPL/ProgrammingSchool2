package pl.coderslab.programmingSchool.servlets;

import org.apache.log4j.Logger;
import pl.coderslab.programmingSchool.models.Solution;
import pl.coderslab.programmingSchool.models.UserGroup;
import pl.coderslab.programmingSchool.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/admin/group_managing")
public class GroupManaging extends HttpServlet {

    public static final Logger logger = Logger.getLogger(GroupManaging.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (Connection conn = DbUtil.getConnection()) {

            ArrayList<UserGroup> userGroups = UserGroup.loadAllGroups(conn);

            req.setAttribute("userGroups", userGroups);
            getServletContext().getRequestDispatcher("/WEB-INF/views/group_managing.jsp").forward(req, resp);

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}