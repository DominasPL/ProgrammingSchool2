package pl.coderslab.programmingSchool.models;

import pl.coderslab.programmingSchool.dao.ExerciseDao;
import pl.coderslab.programmingSchool.dao.SolutionDao;
import pl.coderslab.programmingSchool.dao.UserDao;
import pl.coderslab.programmingSchool.utils.DbUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    private int id;
    private int exercise_id;
    private int user_id;
    private String created;
    private String updated;
    private String description;

    public Solution() {

    }

    public Solution(int exercise_id, int user_id, String created, String updated, String description) throws SQLException {
        this.exercise_id = SolutionDao.checkExerciseID(exercise_id);
        this.user_id = SolutionDao.checkUserID(user_id);
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) throws SQLException {
        this.exercise_id = SolutionDao.checkExerciseID(exercise_id);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) throws SQLException{
        this.user_id = SolutionDao.checkUserID(user_id);
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", exercise_id=" + exercise_id +
                ", user_id=" + user_id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
