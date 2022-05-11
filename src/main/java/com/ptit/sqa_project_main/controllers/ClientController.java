package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public String index(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Client> clients = clientService.getAll();
        List<Client> _clients = new ArrayList<>();
        System.out.println("Search: " + search);
        if(search != null) {
            try {
                String _search = URLDecoder.decode(search, StandardCharsets.UTF_8.toString());
                for(Client client: clients) {
                    if(client.getName().contains(_search)) {
                        _clients.add(client);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            }
        } else {
            _clients.addAll(clients);
        }

        model.addAttribute("clients", _clients);
        model.addAttribute("search", search);

        return "client-management";
    }

    @GetMapping("/clients/{id}")
    public String show(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Client client = clientService.getById(id);

            model.addAttribute("client", client);

            return "client-detail";
        } catch (Throwable e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/clients";
        }
    }

    @GetMapping("/clients/{id}/bills")
    public String showBills(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            List<Bill> bills = clientService.getClientBill(id);

            model.addAttribute("bills", bills);

            return "client-bills";
        } catch (Throwable e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/clients";
        }
    }
}
