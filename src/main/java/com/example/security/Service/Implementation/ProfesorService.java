package com.example.security.Service.Implementation;

import com.example.security.Models.Ocena;
import com.example.security.Models.Student;
import com.example.security.Repository.ORepository;
import com.example.security.Repository.SRepository;
import com.example.security.Service.Interface.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService implements IProfesorService {
    @Autowired
    private ORepository oRepository;
    @Autowired
    private SRepository sRepository;

    @Override
    public List<Ocena> findOceni(int indeks) {
        return oRepository.findAWithIndex(indeks);
    }

    @Override
    public Student getStudent(int indeks) {
        return SRepository.findStudent(indeks);
    }
    @Override
    public List<Student> getAllStudents(){

        return sRepository.findAll();

    }


}
