package com.abhijith.SpringJDBCx.Repo;


import com.abhijith.SpringJDBCx.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void save(Student s) {
        try {
            System.out.println("Connecting to PostgreSQL to save/update student...");

            // Check if roll_no already exists
            String checkSql = "SELECT COUNT(*) FROM student WHERE roll_no = ?";
            int count = jdbc.queryForObject(checkSql, new Object[]{s.getRoll_no()}, Integer.class);


            if ("Anu".equalsIgnoreCase(s.getName())) {
                s.setName("Aleena");
                System.out.println("Name changed from 'Anu' to 'Aleena' for Roll No: " + s.getRoll_no());
            }

            if (count > 0) {
                // Update student
                String updateSql = "UPDATE student SET name = ?, mark = ? WHERE roll_no = ?";
                int rows = jdbc.update(updateSql, s.getName(), s.getMark(), s.getRoll_no());
                System.out.println("Executed update query. Rows affected: " + rows);
            } else {
                // Insert new student
                String insertSql = "INSERT INTO student(roll_no, name, mark) VALUES (?, ?, ?)";
                int rows = jdbc.update(insertSql, s.getRoll_no(), s.getName(), s.getMark());
                System.out.println("Executed insert query. Rows affected: " + rows);
            }
        } catch (Exception e) {
            System.err.println("Error interacting with PostgreSQL: " + e.getMessage());
            throw e;
        }
    }



    public List<Student> findAll() {
        System.out.println("Connecting to PostgreSQL to retrieve all students...");
        String checkQuery = "SELECT roll_no, name, mark FROM student";

        return jdbc.query(checkQuery, (rs, rowNum) -> {
            System.out.println("Retrieved row from PostgreSQL: Roll No: " + rs.getInt("roll_no")
                    + ", Name: " + rs.getString("name") + ", Mark: " + rs.getDouble("mark"));
            Student s = new Student();
            s.setRoll_no(rs.getInt("roll_no"));
            s.setName(rs.getString("name"));
            s.setMark(rs.getDouble("mark"));
            return s;
        });
    }


    public void checkDatabaseConnection() {
        try {
            System.out.println("Checking connection to PostgreSQL...");
            jdbc.execute("SELECT 1");
            System.out.println("Successfully connected to PostgreSQL!");
        } catch (Exception e) {
            System.err.println("Failed to connect to PostgreSQL: " + e.getMessage());
        }
    }

}
