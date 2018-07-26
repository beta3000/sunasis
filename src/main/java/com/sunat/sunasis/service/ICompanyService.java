package com.sunat.sunasis.service;

import com.sunat.sunasis.model.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompanyService {

    CompanyModel  findByRazonSocialOrRuc(String razonSocial,String ruc);

    Page<CompanyModel> getAll(Pageable pageable);

    CompanyModel getById(Long id);

    Page<CompanyModel> findByRazonSocialContains(String razonSocial,Pageable pageable);

    CompanyModel findByRuc(String ruc);
}
