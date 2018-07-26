package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
    CompanyModel findByRazonSocialOrRuc(String razonSocial, String ruc);

    Page<CompanyModel> findAll(Pageable pageable);

    Page<CompanyModel> findByRazonSocialContains(String razonSocial, Pageable pageable);

    CompanyModel findByRuc(String ruc);
}
