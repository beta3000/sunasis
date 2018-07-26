package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
}
