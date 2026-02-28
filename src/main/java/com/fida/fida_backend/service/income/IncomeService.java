package com.fida.fida_backend.service.income;

import com.fida.fida_backend.dto.IncomeDto;
import com.fida.fida_backend.entity.Income;
import com.fida.fida_backend.entity.User;
import com.fida.fida_backend.repository.income.IncomeRepository;
import com.fida.fida_backend.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IncomeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IncomeRepository incomeRepository;
    public ResponseEntity<?> record(HttpServletRequest httpServletRequest,Income income) {
        Long userId = (Long) httpServletRequest.getAttribute("userId");

        return userRepository.findById(userId).map(user -> {
            // 1. Bu kullanıcıya ait halihazırda bir gelir kaydı var mı bak?
            Income existingIncome = incomeRepository.findByUser(user);

            if (existingIncome != null) {
                // 2. Varsa, yeni kaydetmek yerine mevcudu güncelle (UPDATE)
                existingIncome.setIncome(income.getIncome());
                Income updated = incomeRepository.save(existingIncome);
                return ResponseEntity.ok(new IncomeDto(updated.getId(), updated.getIncome()));
            } else {
                // 3. Yoksa, yeni bir kayıt oluştur (INSERT)
                Income newIncome = new Income();
                newIncome.setIncome(income.getIncome());
                newIncome.setUser(user);
                Income saved = incomeRepository.save(newIncome);
                return ResponseEntity.ok(new IncomeDto(saved.getId(), saved.getIncome()));
            }
        }).orElse(ResponseEntity.notFound().build());
    }
    public Income list(HttpServletRequest httpServletRequest) {
        // userId'nin tipine dikkat! User entity'sinde Integer ise intValue() kullanmalısın.
        Long userId = (Long) httpServletRequest.getAttribute("userId");

        return incomeRepository.findByUserId(userId.intValue())
                .orElse(new Income()); // Bulamazsa boş bir Income objesi döner
    }
    public ResponseEntity<?> delete(HttpServletRequest httpServletRequest){
        Long id = (Long) httpServletRequest.getAttribute("userId");
        List<Income> incomes = incomeRepository.findAll();
        Integer newincome = 0;
        for (Income income: incomes){
            if (Objects.equals(income.getUser().getId(), id)){
                newincome=income.getId();
            }

        }

        return ResponseEntity.ok(incomeRepository.deleteById(newincome));
    }
}
