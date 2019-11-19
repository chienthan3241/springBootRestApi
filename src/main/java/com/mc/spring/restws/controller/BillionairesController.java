package com.mc.spring.restws.controller;

import com.mc.spring.restws.exception.BadRequestException;
import com.mc.spring.restws.exception.ResourceNotFoundException;
import com.mc.spring.restws.model.Billionaires;
import com.mc.spring.restws.service.BillionairesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class BillionairesController {

    @Autowired
    private BillionairesService billionairesService;

    public void setBillionairesService(BillionairesService service) {
        this.billionairesService = service;
    }

    @GetMapping("/billionaires")
    public List<Billionaires> getAllBillionnaires() {
        return billionairesService.getAllBillionaires();
    }

    @GetMapping("/billionaires/{billionairesId}")
    public Billionaires getBillionaires(@PathVariable(name = "billionairesId") Long id) {
        try {
            Billionaires result = billionairesService.getBillionaires(id);
            return result;
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Id: " + id + " not found!");
        }
    }

    @PostMapping("/billionaires")
    public void saveBillionaires(Billionaires billionaires) {
        try {
            billionairesService.getBillionaires(billionaires.getId());
            throw new BadRequestException("Object Id: " + billionaires.getId() + " is exist!");
        } catch (NoSuchElementException e) {
            billionairesService.saveBillionaires(billionaires);
        }
    }

    @DeleteMapping("/billionaires/{billionairesId}")
    public void deleteBillionaires(@PathVariable(name = "billionairesId") Long id) {
        try {
            billionairesService.getBillionaires(id);
            billionairesService.deleteBilionaires(id);
        } catch (NoSuchElementException e) {
            throw new BadRequestException("Id: " + id + " not found.");
        }

    }

    @PutMapping("/billionaires/{billionairesId}")
    public void updateBillionaires(@PathVariable(name = "billionairesId") Long id,
                                   @RequestBody Billionaires billi) {
        try {
            billionairesService.getBillionaires(id);
            billionairesService.updateBillionaires(billi);
        } catch (NoSuchElementException e) {
            throw new BadRequestException("Id: " + id + " not found.");
        }
    }
}
