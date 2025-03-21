package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookModel>> listarLivros() {
        List<BookModel> list = bookService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<BookModel> criarLivro(@Valid @RequestBody BookModel bookModel) {
        BookModel novoLivro = bookService.criarLivro(bookModel);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoLivro.getId()).toUri();
        
        return ResponseEntity.created(uri).body(novoLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        try {
            bookService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> atualizarLivro(@PathVariable Long id, @Valid @RequestBody BookModel bookModel) {
        try {
            BookModel livroAtualizado = bookService.update(id, bookModel);
            return ResponseEntity.ok().body(livroAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 