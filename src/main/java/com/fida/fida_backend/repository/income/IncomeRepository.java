package com.fida.fida_backend.repository.income;

import com.fida.fida_backend.entity.Income;
import com.fida.fida_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    Object deleteById(Integer newincome);

    Income findByUser(User user);

    Optional<Income> findByUserId(int i);
}
