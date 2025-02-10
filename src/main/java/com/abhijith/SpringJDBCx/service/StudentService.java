package com.abhijith.SpringJDBCx.service;


import com.abhijith.SpringJDBCx.Repo.StudentRepository;
import com.abhijith.SpringJDBCx.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public StudentRepository getRepo() {
        return repo;
    }
    @Autowired
    public void setRepo(StudentRepository repo) {
        this.repo = repo;
    }

    private StudentRepository repo;

    public void addStudent(Student s){
        repo.save(s);
    }

    public List<Student> getStudent() {

        return repo.findAll();
    }


}
