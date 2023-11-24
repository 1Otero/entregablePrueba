package com.example.listaCompra.controller;

import com.example.listaCompra.entity.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ini")
public class InicioController {
    @GetMapping("/")
    public String getSaludo(){
        return "hello Admin";
    }

    @GetMapping("/getcompra")
    public List<product> getCompra(){
     product p= new product();
     p.setNombre("Manzana");
     p.setCantidad(2);
     p.setPrecio(2000);
     //return Arrays.asList(new product(), new product());
     return Arrays.asList(p);
    }
}
