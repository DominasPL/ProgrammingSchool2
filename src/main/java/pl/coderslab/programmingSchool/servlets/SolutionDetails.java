package pl.coderslab.programmingSchool.servlets;


import org.apache.log4j.Logger;
import pl.coderslab.programmingSchool.dao.SolutionDao;
import pl.coderslab.programmingSchool.models.Solution;
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

@WebServlet("/solution_detail")
public class SolutionDetails extends HttpServlet {

    public static final Logger logger = Logger.getLogger(SolutionDetails.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String par = req.getParameter("id");

        try {

            int id = Integer.parseInt(par);

            try (Connection conn = DbUtil.getConnection()) {

                Solution solution = SolutionDao.loadSolutionById(conn, id);

                req.setAttribute("solution", solution);
                getServletContext().getRequestDispatcher("/WEB-INF/views/solution_details.jsp").forward(req, resp);


            } catch (SQLException e) {
                resp.getWriter().println("Błąd!");
            }



        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }
}
