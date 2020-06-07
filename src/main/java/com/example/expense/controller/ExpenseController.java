package com.example.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense.model.Expense;
import com.example.expense.repository.ExpenseRepository;

@RestController
@RequestMapping("/api")
public class ExpenseController {
	private  ExpenseRepository expenseRepository;

	public ExpenseController(ExpenseRepository expenseRepository) {
		super();
		this.expenseRepository = expenseRepository;
	}
	
	@GetMapping("/expenses")
	List<Expense> getExpenses() {
		return expenseRepository.findAll();
	}

	@DeleteMapping("/expense/{id}")
	ResponseEntity<?> deleteExpense(@PathVariable Long id) {
		expenseRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/expense")
	ResponseEntity<Expense> createExpense(@Validated @RequestBody Expense expense) throws URISyntaxException {
		System.out.println(expense);
		Expense result = expenseRepository.save(expense);
		return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);
	}
}
