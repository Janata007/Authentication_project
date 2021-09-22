package com.example.security.Service.Interface;

import com.example.security.Models.Ocena;
import com.example.security.Models.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProfesorService {

    List<Ocena> findOceni(int indeks);

    Student getStudent(int indeks);

    List<Student> getAllStudents();


}
