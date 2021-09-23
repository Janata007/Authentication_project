package com.example.security.Controller;

import com.example.security.Models.Ocena;
import com.example.security.Models.Student;
import com.example.security.Service.Interface.IOceniService;
import com.example.security.Service.Interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private IOceniService oceniService;
    @Autowired
    private IStudentService studentService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/oceni")
    public String findOceni(Model model) {
        List<Ocena> oceni = oceniService.findAll();
        //System.out.println(oceni);
        model.addAttribute("oceni", oceni);
        return "showOceni";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/showStudent")
    public String findStudent(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String in = auth.getName(); //indeksot
        int indeks = Integer.parseInt(in);
       Student s = studentService.findStudent(indeks);
       System.out.println(s);
        model.addAttribute("student", s);
        return "showStudent";
    }


}
