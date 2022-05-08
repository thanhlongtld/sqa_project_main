package com.ptit.sqa_project_main.seeders;

import com.ptit.sqa_project_main.repositories.ClientRepository;

public class ClientSeeder {
    //    @Autowired
    private ClientRepository clientRepository;

    public static void genClients() {
        clientRepository.deleteAll();

        for (int i = 1; i <= 1000; i++) {
            
        }

    }
}
