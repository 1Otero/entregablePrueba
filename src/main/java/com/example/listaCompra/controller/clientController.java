package com.example.listaCompra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.listaCompra.entity.client;
import com.example.listaCompra.service.clientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class clientController {
    @Autowired
    private clientService client;
    @GetMapping("/")
    public List<client> allCLient(){
        return client.getAllClients();
    }
    @GetMapping("/getcli")
    public ResponseEntity getClient(@RequestParam(defaultValue = "1") int id){
        try {
            int id_client= id;
            client cli= client.getCLient(id_client);
            return ResponseEntity.ok().body(cli);
        }catch (Exception e){
            System.out.println("Error getcli");
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/savecli")
    public ResponseEntity saveClient(@RequestBody client cli){
     try{
        if(cli != null){
            client cliSave= client.saveClient(cli);
            return ResponseEntity.ok().body(cliSave);
        }else{
            return ResponseEntity.notFound().build();
        }
     }catch(Exception e){
        System.out.println("Error saveClient");
        System.out.println(e);
        return ResponseEntity.badRequest().build();
     }
    }
    @PutMapping("/updatecli/{idclient}")
    public ResponseEntity updateClient(@RequestParam(defaultValue = "10", value = "idclient") int idClient,@RequestBody client client1){
        int id= idClient;
        try {
            if(client1 != null){
                client cli= client.getCLient(id);
                cli.setNombre(client1.getNombre());
                cli.setRole(client1.getRole());
                cli.setEdad(client1.getEdad());
                client.updateClient(cli);
                return ResponseEntity.ok().body(cli);
            }else{
                return ResponseEntity.ok().body("Debe ingresar los datos modificados");
            }
        }catch(Exception e){
            System.out.println("Error updateCli");
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletecli/{id}")
    public ResponseEntity deleteClient(@RequestParam(defaultValue = "10", value = "id") int idClient){
        try{
            boolean delete= client.deleteClient(idClient);
            if (delete){
                return ResponseEntity.ok().body("deleted sucessful");
            }else{
                return ResponseEntity.ok().body("Not delete");
            }
        }catch (Exception e){
            System.out.println("Error deleteClient");
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
