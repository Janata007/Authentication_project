package com.example.security.Service.Interface;

import com.example.security.Models.Ocena;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IOceniService {
    List<Ocena> findAll();
    void insertOcena(Ocena ocena);
}
