package pl.coderslab.programmingSchool.servlets;

import org.apache.log4j.Logger;
import pl.coderslab.programmingSchool.dao.UserGroupDao;
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

@WebServlet("/admin/group_managing/edit_group")
public class EditGroup extends HttpServlet {

    public static final Logger logger = Logger.getLogger(EditGroup.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        int id = Integer.parseInt(param1);

        req.setAttribute("id", id);
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_group.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        String param2 = req.getParameter("name");


        int id = Integer.parseInt(param1);

        try (Connection conn = DbUtil.getConnection()) {

            UserGroup userGroup = UserGroupDao.loadGroupByID(conn, id);
            userGroup.setName(param2);
            UserGroupDao.saveGroupToDb(conn, userGroup);

            resp.sendRedirect("/admin/group_managing");

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }



    }
}
