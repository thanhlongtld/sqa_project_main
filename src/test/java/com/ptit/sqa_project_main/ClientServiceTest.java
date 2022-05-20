package com.ptit.sqa_project_main;


import com.ptit.sqa_project_main.exceptions.NotFoundException;
import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import com.ptit.sqa_project_main.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;

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
            clientService.getById(666);
            Assertions.fail("Not throw exception");
        } catch (NotFoundException e) {
            Assertions.assertEquals("Client not found",e.getMessage());
        }

    }

    @Test
    public void testGetClientBill(){
        // client co bill
        ClientService clientService = context.getBean(ClientService.class);

        List<Bill> bills = clientService.getClientBill(3);

        Assertions.assertEquals(3, bills.get(0).getClient().getId());
        Assertions.assertEquals(3, bills.get(1).getClient().getId());
    }

    @Test
    public void testGetClientBill1(){
        // client ko co bill
        ClientService clientService = context.getBean(ClientService.class);

        List<Bill> bills = clientService.getClientBill(4);

        Assertions.assertEquals(0,bills.size());
    }

    @Rollback(true)
    @Test
    public void testGetAllClientsWithSearch(){
        ClientService clientService = context.getBean(ClientService.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        List<Client> clients = clientService.getAllClientsWithSearch(null);

        Assertions.assertEquals(1000,clients.size());

        Client client = new Client();
        client.setName("HTML");
        client.setClientCode("html");
        client.setEmail("html@gmail.com");
        client.setPhone("0987654321");
        client.setNationalId("9862394");
        clientRepository.save(client);

        List<Client> _clients = clientService.getAllClientsWithSearch("HTML");
        Assertions.assertEquals(1,_clients.size());
        Assertions.assertEquals(client.toString(),_clients.get(0).toString());
    }
}
