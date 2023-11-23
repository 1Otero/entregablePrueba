package com.example.listaCompra.controller;

import com.example.listaCompra.entity.product;
import com.example.listaCompra.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.listaCompra.service.productService;
import java.util.List;

@RequestMapping("/product")
@RestController
public class productController {
    @Autowired
    private productService productService;
    @GetMapping("/")
    public List<product> allProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/getprod")
    public ResponseEntity getProduct(@RequestParam(defaultValue = "1") int id){
        try {
            int id_Product= id;
            product prod= productService.getProduct(id_Product);
            return ResponseEntity.ok().body(prod);
        }catch(Exception e){
            System.out.println("Error getProduct");
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/saveprod")
    public ResponseEntity saveProduct(@RequestBody product prod){
        try {
            if(prod != null){
                product cliSave= productService.saveProduct(prod);
                return ResponseEntity.ok().body(cliSave);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            System.out.println("Error saveProduct");
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/updateprod/{idproduct}")
    public ResponseEntity updateProduct(@RequestParam(value = "idproduct") int idProduct,@RequestBody product product1){
        try {
            product prod= productService.getProduct(idProduct);
            if(product1 != null){
                prod.setNombre(product1.getNombre());
                prod.setCantidad(product1.getCantidad());
                prod.setPrecio(product1.getPrecio());
                productService.saveProduct(prod);
            }
            return ResponseEntity.ok().body("update sucessful");

        }catch(Exception e){
            System.out.println("Error updatecli");
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/deleteprod/{id}")
    public ResponseEntity deleteProduct(@RequestParam(defaultValue = "10", value = "id") int idProduct){
        try{
            boolean delete= productService.deleteProduct(idProduct);
            if(delete){
                return ResponseEntity.ok().body("deleted sucessful");
            }else{
                return ResponseEntity.ok().body("Not delete");
            }
        }catch (Exception e){
            System.out.println("Error deleteProduct");
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
