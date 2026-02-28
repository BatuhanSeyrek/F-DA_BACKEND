package com.fida.fida_backend.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private Integer id;
    private BigDecimal income;
}