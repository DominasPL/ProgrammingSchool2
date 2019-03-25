package pl.coderslab.programmingSchool.servlets;

import pl.coderslab.programmingSchool.models.Exercise;
import pl.coderslab.programmingSchool.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/admin/exercise_managing/add_exercise")
public class AddExercise extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/views/add_exercise.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param2 = req.getParameter("title");
        String param3 = req.getParameter("description");

        try (Connection conn = DbUtil.getConnection()) {

            Exercise exercise = new Exercise();
            exercise.setTitle(param2);
            exercise.setDescription(param3);

            exercise.saveToDb(conn);

            resp.sendRedirect("/admin/exercise_managing");

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }


    }
}
