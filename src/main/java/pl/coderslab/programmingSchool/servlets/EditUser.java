package pl.coderslab.programmingSchool.servlets;

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

@WebServlet("/admin/user_managing/edit_user")
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        int id = Integer.parseInt(param1);

        req.setAttribute("id", id);
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_user.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        String param2 = req.getParameter("group_id");
        String param3 = req.getParameter("username");
        String param4 = req.getParameter("password");
        String param5 = req.getParameter("email");


        int id = Integer.parseInt(param1);
        int group_id = Integer.parseInt(param2);

        try (Connection conn = DbUtil.getConnection()) {
            User user = User.loadUserById(conn, id);
            user.setGroup_id(group_id);
            user.setUsername(param3);
            user.setPassword(param4);
            user.setEmail(param5);

            user.saveUserToDb(conn);

            resp.sendRedirect("/admin/user_managing");

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }


    }
}
