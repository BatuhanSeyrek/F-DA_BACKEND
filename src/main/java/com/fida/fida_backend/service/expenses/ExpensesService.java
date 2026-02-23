package com.fida.fida_backend.service.expenses;

import com.fida.fida_backend.dto.DtoExpenses;
import com.fida.fida_backend.entity.Expenses;
import com.fida.fida_backend.entity.User;
import com.fida.fida_backend.repository.expenses.ExpensesRepository;
import com.fida.fida_backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> expensesList(HttpServletRequest httpServletRequest) {
        // 1. userId'yi al
        Object id = httpServletRequest.getAttribute("userId");

        if (id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı bilgisi bulunamadı.");
        }

        // 2. Hata Alan Kısım: Güvenli Tip Dönüşümü
        // Doğrudan (Integer) cast yapmak yerine Number üzerinden intValue() alıyoruz.
        // Bu yöntem hem Long hem Integer gelen veriler için güvenlidir.
        Integer userId = ((Number) id).intValue();

        // 3. Repository sorgusu
        List<Expenses> expensesList = expensesRepository.findByUserId(userId);

        // 4. Entity -> DTO Dönüşümü
        List<DtoExpenses> dtoExpensesList = expensesList.stream().map(expense -> {
            DtoExpenses dto = new DtoExpenses();
            dto.setAmount(expense.getAmount());
            dto.setDetails(expense.getDetails());
            dto.setType(expense.getType());
            dto.setCreatedAt(expense.getCreatedAt());
            dto.setUser_id(expense.getUser().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtoExpensesList);
    }
}
