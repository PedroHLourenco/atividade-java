package com.crud.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    private ResponseEntity<List<ProductModel>> listarProdutos(){
       List<ProductModel> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    private ResponseEntity<ProductModel> criarProduto(@RequestBody ProductModel productModel){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productModel.getId()).toUri();
        ProductModel response = productService.criarProduto(productModel);
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deletarProduto(@PathVariable Long id){
        productService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductModel> update(@PathVariable Long id, @RequestBody ProductModel productModel){
         ProductModel response  = productService.update(id, productModel);
        return ResponseEntity.ok().body(response);
    }

}
