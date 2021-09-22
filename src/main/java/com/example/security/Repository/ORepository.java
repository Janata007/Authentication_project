package com.example.security.Repository;

import com.example.security.Models.Ocena;
import com.example.security.Models.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Repository
public class ORepository {

    public void insertOcena(Ocena ocena){


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            /*Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM oceni ",ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = prepared.executeQuery();

            rs.moveToInsertRow();
            rs.updateLong("id", ocena.getId());
            rs.updateInt("ocena",ocena.getOcenka());
            rs.updateString("predmet", ocena.getPredmet());
            rs.insertRow();*/
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM oceni");
            rs.moveToInsertRow();
            rs.updateLong("id", ocena.getId());
            rs.updateInt("ocena", ocena.getOcenka());
            rs.updateString("predmet", ocena.getPredmet());
            rs.updateInt("student_id", ocena.getStudent_id());
            rs.insertRow();
            rs.moveToCurrentRow();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Ocena> findAll() {

        List ll = new LinkedList();
        Connection c = null;
        int sId = 0;
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = c.createStatement();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String target = auth.getName(); //indeksot

            String targetID = String.valueOf(target);

            PreparedStatement prepared = c.prepareStatement("SELECT * FROM student WHERE indeks = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                int i = rs.getInt("id");
                int o = rs.getInt("indeks");
                String str = rs.getString("ime");
                sId = i;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String target = auth.getName(); //indeksot

            String targetID = String.valueOf(sId);

            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM oceni WHERE student_id = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                Long i = rs.getLong("id");
                int o = rs.getInt("ocena");
                String str = rs.getString("predmet");

                Ocena ocenka = new Ocena(i, o, str,sId);

                ll.add(ocenka);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ll;
    }


    public List<Ocena> findAWithIndex(int indeks) {

        List ll = new LinkedList();
        Connection c = null;
        int sId = 0;
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = c.createStatement();

            String targetID = String.valueOf(indeks);

            PreparedStatement prepared = c.prepareStatement("SELECT * FROM student WHERE indeks = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                int i = rs.getInt("id");
                int o = rs.getInt("indeks");
                String str = rs.getString("ime");
                sId = i;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ib_security", "root", "root");
            Statement statement = connection.createStatement();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String target = auth.getName(); //indeksot

            String targetID = String.valueOf(sId);

            PreparedStatement prepared = connection.prepareStatement("SELECT * FROM oceni WHERE student_id = ?;");
            prepared.setString(1, targetID);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                Long i = rs.getLong("id");
                int o = rs.getInt("ocena");
                String str = rs.getString("predmet");

                Ocena ocenka = new Ocena(i, o, str,sId);

                ll.add(ocenka);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ll;


}
}




