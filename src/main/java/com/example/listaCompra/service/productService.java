package com.example.listaCompra.service;

import com.example.listaCompra.entity.product;
import com.example.listaCompra.entity.product;
import com.example.listaCompra.repository.productRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    private productRepository productRepository1;
    public productService(productRepository productR){
        this.productRepository1= productR;
    }
    public List<product> getAllProduct(){
        try{
            return productRepository1.findAll();
        }catch(Exception e){
            System.out.println("Error findAllproducts");
            System.out.println(e);
            return null;
        }

    }
    public product getProduct(int id){
        try{
            return productRepository1.findById(id).get();
        }catch(Exception e){
            System.out.println("Error findproducts");
            System.out.println(e);
            return null;
        }
    }
    public product saveProduct(product product1){
        try{
            product cli= productRepository1.save(product1);
            return cli;
        }catch(Exception e){
            System.out.println("Error saveproduct");
            System.out.println(e);
            return null;
        }
    }
    public product updateProduct(product product1){
        try {
            product cli= productRepository1.save(product1);
            return cli;
        }catch (Exception e){
            System.out.println("Error updateproduct");
            System.out.println(e);
            return null;
        }
    }
    public boolean deleteProduct(int delete){
        try {
            productRepository1.deleteById(delete);
            System.out.println("Se elimino exitosamente el product");
            return true;
        }catch (Exception e){
            System.out.println("Error deleteproduct");
            System.out.println(e);
            return false;
        }
    }
}
