package com.fida.fida_backend.repository.expenses;

import com.fida.fida_backend.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByUserId(Integer id);
}
