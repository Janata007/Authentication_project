package com.example.security.Service.Implementation;

import com.example.security.Models.Student;
import com.example.security.Repository.SRepository;
import com.example.security.Service.Interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private SRepository studentRepository;

    public StudentService(SRepository sRepository){ this.studentRepository = sRepository;}



    @Override
    public Student findStudent(int indeks) {
        Student s = studentRepository.findStudent(indeks);
        return s;
    }

    @Override
    public Student findCurrentStudent() {
        Student s = studentRepository.findCurrentStudent();
        return s;
    }
}
