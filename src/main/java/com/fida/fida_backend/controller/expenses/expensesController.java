package com.fida.fida_backend.controller.expenses;


import com.fida.fida_backend.entity.Expenses;
import com.fida.fida_backend.service.expenses.ExpensesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class expensesController {
    @Autowired
    ExpensesService expensesService;
    @PostMapping("/record")
    public ResponseEntity<?> record(HttpServletRequest httpServletRequest, @RequestBody Expenses expenses){
        return expensesService.record(httpServletRequest,expenses);
    }
    @GetMapping("/expensesList")
    public ResponseEntity<?> expensesList(HttpServletRequest httpServletRequest){
        return expensesService.expensesList(httpServletRequest);
    }
}
