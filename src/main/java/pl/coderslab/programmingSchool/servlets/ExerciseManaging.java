package pl.coderslab.programmingSchool.servlets;


import pl.coderslab.programmingSchool.models.Exercise;
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

@WebServlet("/admin/exercise_managing")
public class ExerciseManaging extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = DbUtil.getConnection()) {

            ArrayList<Exercise> exercises = Exercise.loadAllExercises(conn);

            req.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/WEB-INF/views/exercise_managing.jsp").forward(req, resp);

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }

    }
}
