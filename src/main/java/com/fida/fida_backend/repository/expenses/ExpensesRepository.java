package com.fida.fida_backend.repository.expenses;

import com.fida.fida_backend.entity.user.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
}
