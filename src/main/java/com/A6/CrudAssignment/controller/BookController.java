package com.A6.CrudAssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.A6.CrudAssignment.entity.Book;
import com.A6.CrudAssignment.repository.BookRepository;

@Controller
public class BookController {
	@Autowired
	BookRepository repository;
	
	@GetMapping("/")
	public String loadMain() {
		return "main.html";
	}
	
	@GetMapping("/insert")
	public String loadInsertForm() {
		return "insert.html";
	}
	
	@PostMapping("/insert")
	public String saveRecord(Book book, ModelMap map) {
		repository.save(book); 
		map.put("message", "Book Added Successfully!"); 
		return "main.html";
	}
	
	@GetMapping("/fetch")
	public String fetch(ModelMap map) {
		List<Book> books = repository.findAll();
		map.put("books", books);
		return "fetch.html";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRecord(@PathVariable Long id, ModelMap map) {
		repository.deleteById(id);
		map.put("message", "Book is deleted successfully!");
		return "main.html";
	}
	
	@GetMapping("/edit/{id}")
	public String loadEditForm(@PathVariable Long id, ModelMap map) {
		Book book = repository.findById(id).get(); 
		map.put("book", book);
		return "edit.html";
	}
	
	@PostMapping("/edit")
	public String saveEditedRecord(Book book, ModelMap map) {
		repository.save(book); 
		map.put("message", "Book Updated Successfully!");
		return "main.html";
	}
	

}
