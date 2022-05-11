package com.ptit.sqa_project_main.services;


import com.ptit.sqa_project_main.exceptions.NotFoundException;
import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.repositories.BillRepository;
import com.ptit.sqa_project_main.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BillRepository billRepository;

    public List<Client> getAll() {
        return (List<Client>) this.repository.findAll();
    }

    public List<Client> getClientsByFilter(String search) {
        String _search = "%"+search+"%";
        System.out.println(_search);
        return (List<Client>) this.repository.getClientsByNameLike(_search);
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
}
