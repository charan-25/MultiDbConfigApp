package com.multi.db.config.app.rest;

import com.multi.db.config.app.primary.entities.Users;
import com.multi.db.config.app.primary.repo.PrimaryRepo;
import com.multi.db.config.app.secondary.entities.Books;
import com.multi.db.config.app.secondary.repo.SecondaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("multi-db")
public class MultiDbRest {

    @Autowired
    private SecondaryRepo secondaryRepo;

    @Autowired
    private PrimaryRepo primaryRepo;

    @GetMapping("/add-data")
    public String addData(){
        primaryRepo.saveAll(Stream.of(new Users("Sahishnav"),new Users("Sai Charan")).collect(Collectors.toList()));
        secondaryRepo.saveAll(Stream.of(new Books("UPSC"),new Books("Design Patterns")).collect(Collectors.toList()));
        return "Data Added Successfully";
    }

    @GetMapping("/get-users")
    public List<Users> getUsers(){
        return primaryRepo.findAll();
    }

    @GetMapping("/get-books")
    public List<Books> getBooks(){
        return secondaryRepo.findAll();
    }
}
