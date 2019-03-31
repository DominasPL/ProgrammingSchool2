package pl.coderslab.programmingSchool.servlets;

import org.apache.log4j.Logger;
import pl.coderslab.programmingSchool.dao.UserDao;
import pl.coderslab.programmingSchool.models.Solution;
import pl.coderslab.programmingSchool.models.User;
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

@WebServlet("/groups/group_users")
public class GroupUsers extends HttpServlet {

    public static final Logger logger = Logger.getLogger(GroupUsers.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String par = req.getParameter("id");

        try {

            int id = Integer.parseInt(par);

            try (Connection conn = DbUtil.getConnection()) {

                ArrayList<User> users = UserDao.loadAllUsersFromGroup(conn, id);

                req.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/WEB-INF/views/group_users.jsp").forward(req, resp);


            } catch (SQLException e) {
                resp.getWriter().println("Błąd!");
            }



        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



    }
}
