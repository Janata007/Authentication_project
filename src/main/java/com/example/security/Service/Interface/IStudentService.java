package com.example.security.Service.Interface;

import com.example.security.Models.Student;
import org.springframework.stereotype.Component;

@Component
public interface IStudentService {

    Student findStudent(int indeks);

    Student findCurrentStudent();

}
