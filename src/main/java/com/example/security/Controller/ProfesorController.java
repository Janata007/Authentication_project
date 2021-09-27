package com.example.security.Controller;


import com.example.security.Models.Ocena;
import com.example.security.Models.Student;
import com.example.security.Service.Interface.IOceniService;
import com.example.security.Service.Interface.IProfesorService;
import com.example.security.Service.Interface.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.cert.X509Certificate;
import java.util.List;

@Controller
@RequestMapping(path = "/profesor")
public class ProfesorController {
    @Autowired
    private IProfesorService profesorService;
    @Autowired
    private IOceniService oceniService;
    @Autowired
    private IStudentService studentService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/studenti")
    public String findStudent(Model model, HttpServletRequest request) {
        List<Student> studenti = profesorService.getAllStudents();
        System.out.println(studenti);
        model.addAttribute("studenti", studenti);
        return "showAllStudents";

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/studenti/{indeks}")
    public String showStudent(@PathVariable("indeks") int indeks, Model model) {
        List<Ocena> oceni = profesorService.findOceni(indeks);
        model.addAttribute("oceni", oceni);
        model.addAttribute("indeks", indeks);
        return "showStudentOceni";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/studenti/{indeks}/changeocena")
    public String chanegOcena(@PathVariable("indeks") int indeks, Model model) {
        model.addAttribute("indeks", indeks);
        int studentId = studentService.findStudent(indeks).getId();
        model.addAttribute("studentId", studentId);
        return "changeStudentOcena";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(path="/processform")
    public String insertOcenka( HttpServletRequest request){
        String gradeId = request.getParameter("gradeId"); //long
        String grade = request.getParameter("grade"); //int
        String subject = request.getParameter("subject");
        String studentId = request.getParameter("studentId"); //int
        int ocena = Integer.parseInt(grade);
        int idstudent = Integer.parseInt(studentId);
        Long ocenaId = new Long(Integer.parseInt(gradeId));

        Ocena newOcena = new Ocena(ocenaId, ocena, subject, idstudent);
        System.out.println(newOcena.getId());

        oceniService.insertOcena(newOcena);
        return "showStudentOceni";

    }

}
