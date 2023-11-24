package com.example.listaCompra.service;

<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
>>>>>>> 17e94da62ecb87f03ba4ce3a4e76897c89b02beb
import org.springframework.stereotype.Service;
import com.example.listaCompra.repository.clientRepository;
import com.example.listaCompra.entity.client;
import java.util.Arrays;
import java.util.List;

@Service
public class clientService {
    private clientRepository clientRepository;
    public clientService(clientRepository clientR){
        this.clientRepository= clientR;
    }
    public List<client> getAllClients(){
        try{
            return clientRepository.findAll();
        }catch(Exception e){
            System.out.println("Error findAllClients");
            System.out.println(e);
            return null;
        }

    }
    public client getCLient(int id){
        try{
            return clientRepository.findById(id).get();
        }catch(Exception e){
            System.out.println("Error findClients");
            System.out.println(e);
            return null;
        }
    }
    public client saveClient(client client1){
        try{
<<<<<<< HEAD
            String newPass= new BCryptPasswordEncoder().encode(client1.getPassword());
            client1.setPassword(newPass);
=======
>>>>>>> 17e94da62ecb87f03ba4ce3a4e76897c89b02beb
            client cli= clientRepository.save(client1);
            return cli;
        }catch(Exception e){
            System.out.println("Error saveClient");
            System.out.println(e);
            return null;
        }
    }
    public client updateClient(client client1){
         try {
             client cli= clientRepository.save(client1);
             return cli;
         }catch (Exception e){
             System.out.println("Error updateClient");
             System.out.println(e);
             return null;
         }
    }
    public boolean deleteClient(int delete){
        try {
            System.out.println("idDelete");
            System.out.println(delete);
            clientRepository.deleteById(delete);
            return true;
        }catch (Exception e){
            System.out.println("Error deleteClient");
            System.out.println(e);
            return false;
        }
    }
}
