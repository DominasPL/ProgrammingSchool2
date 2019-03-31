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

@WebServlet("/")
public class HomePageServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(HomePageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try (Connection conn = DbUtil.getConnection()) {

            int solutionsNumber = getSolutionsNumber();
            ArrayList<Solution> solutions = SolutionDao.loadAllSolutionsLimited(conn, solutionsNumber);

            req.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);


        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }

    }


    private int getSolutionsNumber() {
        String numberSolutionsParam = getServletContext().getInitParameter("number-solutions");
        try {
            return Integer.parseInt(numberSolutionsParam);
        } catch (NumberFormatException nfe) {
            logger.warn("Nie udalo się odczytać parametru `numer-solutions`", nfe);
            return 5;
        }
    }

}
