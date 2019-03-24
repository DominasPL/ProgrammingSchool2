package pl.coderslab.programmingSchool.servlets;


import org.apache.log4j.Logger;
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

@WebServlet("/admin/group_managing/add_group")
public class AddGroup extends HttpServlet {

    public static final Logger logger = Logger.getLogger(AddGroup.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/views/add_group.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("name");


        try (Connection conn = DbUtil.getConnection()) {

            UserGroup userGroup = new UserGroup();
            userGroup.setName(param1);
            userGroup.saveGroupToDb(conn);

            resp.sendRedirect("/admin/group_managing");

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }


    }
}
