package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.exceptions.NotFoundException;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testGetAll(){
        ClientService clientService = context.getBean(ClientService.class);

        List<Client> clients = clientService.getAll();

        Assertions.assertEquals("8868",clients.get(0).getNationalId());
        Assertions.assertEquals("6686",clients.get(1).getNationalId());
    }

    @Test
    public void testGetbyId() throws NotFoundException {
        //found client
        ClientService clientService = context.getBean(ClientService.class);

        Client client = clientService.getById(1);

        Assertions.assertEquals("8868",client.getNationalId());
    }

    @Test
    public void testGetById(){
        // not found client
        ClientService clientService = context.getBean(ClientService.class);

        try {
            clientService.getById(3);
            Assertions.fail("Not throw exception");
        } catch (NotFoundException e) {
            Assertions.assertEquals("Client not found",e.getMessage());
        }

    }

}
