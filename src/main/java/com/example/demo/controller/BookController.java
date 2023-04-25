package com.example.demo.controller;

import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    /**
     * Postmapping - post - request body whole json
     * Getmapping - get - no params
     * UpdateBook - put - request body
     * deleteBook - delete - book id in request params
     * GetBook - get - book id in request params
     */

    private HashMap<Integer, Book> bookHashMap = new HashMap<>();


    @PostMapping(value ="/book", consumes = {"application/json"})
    private String addBook(@RequestBody Book book){

        if(bookHashMap.containsKey(book.getId())){
            logger.warn("Book already exists");
            return "Book already exists";
        }

        bookHashMap.put(book.getId(), book);
        return "Book added successfully";
    }

    @GetMapping("/book")
    private List<Book> getBooks(){
        logger.info("there are "+bookHashMap.size() + " number of books");
        return bookHashMap.values().stream().collect(Collectors.toList());
    }

    @PutMapping("/book")
    private String updateBook(@RequestBody Book book){
        if(!bookHashMap.containsKey(book.getId()))
            return "Book with id "+ book.getId() +" is not present and hence cannot be updated ";
        bookHashMap.put(book.getId(), book);
        return "Book with id "+ book.getId() +" has been updated ";
    }

    @DeleteMapping("/book/{bookId}")
    private String deleteBook(@PathVariable("bookId") int bookId){
        if(!bookHashMap.containsKey(bookId)){
            return "Book with id "+ bookId +" is not present and hence cannot be deleted ";
        }
        bookHashMap.remove(bookId);
        return "Book with id "+ bookId +" has been deleted ";
    }

    @GetMapping("/book/{bookId}")
    private Book getSingleBook(@PathVariable("bookId") int bookId){
        if(!bookHashMap.containsKey(bookId)){
            logger.warn("Book with id "+ bookId +" is not present and hence cannot be retrieved ");
            return null;
        }
        return bookHashMap.get(bookId);
    }




}
