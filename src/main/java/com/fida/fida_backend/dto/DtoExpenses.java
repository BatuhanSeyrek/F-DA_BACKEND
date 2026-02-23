package com.fida.fida_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fida.fida_backend.entity.ExpensesType;
import com.fida.fida_backend.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoExpenses {
    private BigDecimal amount;
    private String details;
    private ExpensesType type;
    private LocalDateTime createdAt;
    private Integer user_id;
}
