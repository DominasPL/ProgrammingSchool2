package pl.coderslab.programmingSchool.models;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.programmingSchool.dao.UserDao;
import pl.coderslab.programmingSchool.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;


public class User {
    private int id;
    private int group_id;
    private String username;
    private String email;
    private String password;

    public User() {

    }

    public User(int group_id, String username, String password, String email) throws  SQLException {

        this.group_id = UserDao.checkGroupID(group_id);
        this.username = username;
        setPassword(password); // Hashowanie hasła
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) throws SQLException {
        this.group_id = UserDao.checkGroupID(group_id); //Sprawdzenie czy id grupy znajduje sie w bazie danych
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
