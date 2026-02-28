package com.fida.fida_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "[user]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String phoneNumber;
    @Column(name="userName",nullable = false)
    private String userName;
    @Column(name="password",nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude    // Döngüyü kırmak için
    @EqualsAndHashCode.Exclude
    @JsonIgnore // Gelirin JSON içinde sonsuz döngüye girmesini önler
    private Income income;

}