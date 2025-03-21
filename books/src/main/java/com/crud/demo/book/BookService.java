package com.crud.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<BookModel> findAll(){
        return  bookRepository.findAll();
    }

    public BookModel findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrado com ID: " + id));
    }

    public BookModel criarLivro(BookModel bookModel){
        bookModel.setId(null);
        return bookRepository.save(bookModel);
    }

    public void deletarLivro(Long id){
        findById(id);
        bookRepository.deleteById(id);
    }

    public BookModel update(Long id, BookModel bookModel){
        BookModel livroExistente = findById(id);
        
        livroExistente.setCategoria(bookModel.getCategoria());
        livroExistente.setNome(bookModel.getNome());
        
        return bookRepository.save(livroExistente);
    }

}