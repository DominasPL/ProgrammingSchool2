package pl.coderslab.programmingSchool.servlets;

import pl.coderslab.programmingSchool.dao.ExerciseDao;
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

@WebServlet("/admin/exercise_managing/edit_exercise")
public class EditExercise extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        int id = Integer.parseInt(param1);

        req.setAttribute("id", id);
        getServletContext().getRequestDispatcher("/WEB-INF/views/edit_exercise.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1 = req.getParameter("id");
        String param2 = req.getParameter("title");
        String param3 = req.getParameter("description");


        int id = Integer.parseInt(param1);

        try (Connection conn = DbUtil.getConnection()) {

            Exercise exercise = ExerciseDao.loadExerciseById(conn, id);
            exercise.setTitle(param2);
            exercise.setDescription(param3);

            ExerciseDao.saveToDb(conn, exercise);

            resp.sendRedirect("/admin/exercise_managing");

        } catch (SQLException e) {
            resp.getWriter().println("Błąd!");
        }

    }
}


