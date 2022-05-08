package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.Random;

public class ClientSeeder {
    @Autowired
    private ClientRepository clientRepository;

    public void genClients() {
        clientRepository.deleteAll();

        for (int i = 1; i <= 1000; i++) {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));

            Client client = new Client();

            client.setClientCode(generatedString + i);
            client.setName("Client number" + i);
            client.setEmail("client" + i + "@gmail.com");
            client.setPhone("");
        }

    }
}
