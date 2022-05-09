package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Bill;
import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public String index(Model model) {
        List<Client> clients = clientService.getAll();

        model.addAttribute("clients", clients);

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
