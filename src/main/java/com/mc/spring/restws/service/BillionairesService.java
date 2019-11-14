package com.mc.spring.restws.service;

import com.mc.spring.restws.model.Billionaires;

import java.util.List;

public interface BillionairesService {
    public List<Billionaires> getAllBillionaires();
    public Billionaires getBillionaires(Long id);
    public void saveBillionaires(Billionaires billionaires);
    public void deleteBilionaires(Long id);
    public void updateBillionaires(Billionaires billionaires);
}
