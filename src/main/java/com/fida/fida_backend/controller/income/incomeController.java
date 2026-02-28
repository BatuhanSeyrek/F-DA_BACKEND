package com.fida.fida_backend.controller.income;

import com.fida.fida_backend.entity.Income;
import com.fida.fida_backend.service.income.IncomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class incomeController {
    @Autowired
    IncomeService incomeService;
    @PostMapping("/record")
    public ResponseEntity<?> record(HttpServletRequest httpServletRequest,@RequestBody Income income ){
        return incomeService.record(httpServletRequest,income);
    }
    @GetMapping("/list")
    public Income list(HttpServletRequest httpServletRequest){
        return incomeService.list(httpServletRequest);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(HttpServletRequest httpServletRequest){
        return incomeService.delete(httpServletRequest);
    }
}
