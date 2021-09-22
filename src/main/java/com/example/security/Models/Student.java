package com.example.security.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//POJO class for Student
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="name")
public class Student {

  /*  @Enumerated(value = EnumType.STRING)
    private Role role;*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int indeks;
    @Column
    private String ime;
    @Column
    private String prezime;

}
