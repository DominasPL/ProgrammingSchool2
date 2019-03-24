package pl.coderslab.programmingSchool.servlets;


import org.apache.log4j.Logger;
import pl.coderslab.programmingSchool.models.Solution;
import pl.coderslab.programmingSchool.models.User;
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

@WebServlet("/groups/group_users/user_details")
public class UserDetails extends HttpServlet {

    public static final Logger logger = Logger.getLogger(UserDetails.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String par = req.getParameter("id");

        try {

            int id = Integer.parseInt(par);

            try (Connection conn = DbUtil.getConnection()) {

                User user = User.loadUserById(conn, id);
                ArrayList<Solution> userSolutions = Solution.loadAllSolutionsByUserId(conn, id);

                req.setAttribute("user", user);
                req.setAttribute("userSolutions", userSolutions);
                getServletContext().getRequestDispatcher("/WEB-INF/views/user_details.jsp").forward(req, resp);


            } catch (SQLException e) {
                resp.getWriter().println("Błąd!");
            }



        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }
}
