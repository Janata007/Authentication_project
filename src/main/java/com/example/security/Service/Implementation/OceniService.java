package com.example.security.Service.Implementation;

import com.example.security.Models.Ocena;
import com.example.security.Repository.ORepository;
import com.example.security.Service.Interface.IOceniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OceniService implements IOceniService {

    @Autowired
    private ORepository oceniRepository;

    public OceniService(ORepository oceniRepository1) {
        this.oceniRepository = oceniRepository1;
    }

    @Override
    public List<Ocena> findAll() {

        return oceniRepository.findAll();

    }

    @Override
    public void insertOcena(Ocena ocena) {

        oceniRepository.insertOcena(ocena);
    }


}
