package com.crud.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository ProductRepository;


    public List<ProductModel> findAll() {
        return ProductRepository.findAll();
    }

    public ProductModel criarProduto(ProductModel ProductModel) {
        return ProductRepository.save(ProductModel);
    }

    public void deletarProduto(Long id) {
        ProductRepository.deleteById(id);
    }

    public ProductModel update(Long id, ProductModel ProductMode) {
        ProductModel newProduct = ProductRepository.findById(id).get();
        newProduct.setPreco(ProductMode.getPreco());
        newProduct.setNome(ProductMode.getNome());
        newProduct.setQuantidadeEmEstoque(ProductMode.getQuantidadeEmEstoque());
        return ProductRepository.save(newProduct);

    }

}
