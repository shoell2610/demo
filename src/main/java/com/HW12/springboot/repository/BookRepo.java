package com.HW12.springboot.repository;

import com.HW12.springboot.datamodel.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
