package com.fida.fida_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "income")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal income;
    @OneToOne
    @JoinColumn(name = "user_id") // Veritabanındaki kolon adı (mappedBy SİLİNDİ)
    @ToString.Exclude    // Döngüyü kırmak için ÇOK ÖNEMLİ
    @EqualsAndHashCode.Exclude
    @JsonIgnore // JSON döngüsünü engeller
    private User user;
}
