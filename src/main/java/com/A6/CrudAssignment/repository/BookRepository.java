package com.A6.CrudAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.A6.CrudAssignment.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
