package com.sunat.sunasis.service;

import com.sunat.sunasis.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompanyService {

    Company findByRazonSocialOrRuc(String razonSocial, String ruc);

    Page<Company> getAll(Pageable pageable);

    Company getById(Long id);

    Page<Company> findByRazonSocialContains(String razonSocial, Pageable pageable);

    Company findByRazonSocialIgnoreCase(String razonSocial);

    Company findByRuc(String ruc);

    List<Company> getCompaniesByRucOrRazonSocial(String razonSocial, String ruc);
}
