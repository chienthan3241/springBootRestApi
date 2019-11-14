package com.mc.spring.restws.repository;

import com.mc.spring.restws.model.Billionaires;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillionairesRepository extends JpaRepository<Billionaires, Long> {
}
