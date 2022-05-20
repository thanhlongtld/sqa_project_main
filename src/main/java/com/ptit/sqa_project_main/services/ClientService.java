package com.ptit.sqa_project_main.services;


import com.ptit.sqa_project_main.exceptions.NotFoundException;
import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.BillRepository;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private StringService stringService;

    public List<Client> getAll() {
        return (List<Client>) this.repository.findAll();
    }

    public Client getById(Integer id) throws NotFoundException {
        Optional<Client> result = repository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new NotFoundException("Client not found");
    }

    public List<Bill> getClientBill(Integer clientId) {

        return billRepository.findByClientId(clientId);
    }

    public List<Client> getAllClientsWithSearch(String search) {
        List<Client> clients = getAll();
        List<Client> _clients = new ArrayList<>();
        System.out.println("Search: " + search);
        if(search != null) {
            String _search = stringService.decodeUrl(search);
            for(Client client: clients) {
                if(client.getName().contains(_search)) {
                    _clients.add(client);
                }
            }
        } else {
            _clients.addAll(clients);
        }
        return _clients;
    }
}
