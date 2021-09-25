package com.example.security.Repository;

import com.example.security.Models.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SRepository {

    //najdi go po indeks
    public static Student findStudent(int indeks) {
        Student student = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();
            //ResultSet rs = statement.executeQuery("select * from oceni");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            auth.getName(); //indeksot

            String targetID = String.valueOf(indeks);

            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM student WHERE indeks = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                int i = rs.getInt("id");
                int o = rs.getInt("indeks");
                String str = rs.getString("ime");
                String str1 = rs.getString("prezime");
                student = new Student(i, o, str, str1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }


    public Student findCurrentStudent() {
        Student student = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            auth.getName(); //indeksot

            String targetID = String.valueOf(auth.getName());

            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM student WHERE indeks = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                int i = rs.getInt("id");
                int o = rs.getInt("indeks");
                String str = rs.getString("ime");
                String str1 = rs.getString("prezime");

                student = new Student(i, o, str, str1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }


    public List<Student> findAll() {
        Connection connection = null;
        List<Student> ll = new LinkedList<Student>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();

            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM student");
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                int i = rs.getInt("id");
                int indeks = rs.getInt("indeks");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");

                Student student = new Student(i, indeks, ime, prezime);

                ll.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ll;

    }

}
