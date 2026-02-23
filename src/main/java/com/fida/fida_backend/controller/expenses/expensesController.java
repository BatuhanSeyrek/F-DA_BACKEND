package com.fida.fida_backend.controller.expenses;


import com.fida.fida_backend.entity.user.Expenses;
import com.fida.fida_backend.service.expenses.ExpensesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class expensesController {
    @Autowired
    ExpensesService expensesService;
    @PostMapping("/record")
    public ResponseEntity<?> record(HttpServletRequest httpServletRequest, @RequestBody Expenses expenses){
        return expensesService.record(httpServletRequest,expenses);
    }
}
