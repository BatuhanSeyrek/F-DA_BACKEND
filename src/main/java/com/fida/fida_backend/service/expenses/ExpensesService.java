package com.fida.fida_backend.service.expenses;

import com.fida.fida_backend.entity.user.Expenses;
import com.fida.fida_backend.entity.user.User;
import com.fida.fida_backend.repository.expenses.ExpensesRepository;
import com.fida.fida_backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExpensesService {
    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    UserRepository userRepository;
    public ResponseEntity<?> record(HttpServletRequest httpServletRequest,Expenses expenses){
        Object id = httpServletRequest.getAttribute("userId");
        Optional<User> user = userRepository.findById((Long) id);
        Expenses newexpenses = new Expenses();
        newexpenses.setUser(user.get());
        newexpenses.setType(expenses.getType());
        newexpenses.setDetails(expenses.getDetails());
        newexpenses.setAmount(expenses.getAmount());
        newexpenses.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(expensesRepository.save(newexpenses));
    }
}
