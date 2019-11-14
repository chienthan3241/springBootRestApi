package com.mc.spring.restws.service.impl;

import com.mc.spring.restws.model.Billionaires;
import com.mc.spring.restws.repository.BillionairesRepository;
import com.mc.spring.restws.service.BillionairesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillionairesServiceImpl implements BillionairesService {
    @Autowired
    private BillionairesRepository billionairesRepository;

    public void setBillionairesRepository(BillionairesRepository repository) {
        this.billionairesRepository = repository;
    }

    @Override
    public List<Billionaires> getAllBillionaires() {
        return billionairesRepository.findAll();
    }

    @Override
    public Billionaires getBillionaires(Long id) {
        Optional<Billionaires> optionalBillionnaires = billionairesRepository.findById(id);
        return optionalBillionnaires.get();
    }

    @Override
    public void saveBillionaires(Billionaires billionaires) {
        billionairesRepository.save(billionaires);
    }

    @Override
    public void deleteBilionaires(Long id) {
        billionairesRepository.deleteById(id);
    }

    @Override
    public void updateBillionaires(Billionaires billionaires) {
        billionairesRepository.save(billionaires);
    }
}
