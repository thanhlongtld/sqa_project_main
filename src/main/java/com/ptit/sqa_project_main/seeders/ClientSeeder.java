package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ClientSeeder implements CommandLineRunner {
    @Autowired
    private
    ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {

        if (clientRepository.count() == 0) {
            for (int i = 1; i <= 1000; i++) {
                Random random = new Random();

                int phone = 100000000 + random.nextInt(900000000);
                int nationalId = 100000000 + random.nextInt(900000000);

                Client client = new Client();

                client.setClientCode("Client" + i);
                client.setName("Client number" + i);
                client.setEmail("client" + i + "@gmail.com");
                client.setPhone(Integer.toString(phone));
                client.setNationalId(Integer.toString(nationalId));

                clientRepository.save(client);
            }
        }
    }
}
