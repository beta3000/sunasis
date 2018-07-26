package com.sunat.sunasis.service;

import com.sunat.sunasis.model.CompanyModel;
import com.sunat.sunasis.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    public CompanyModel findByRazonSocialOrRuc(String razonSocial, String ruc) {
        return repository.findByRazonSocialOrRuc(razonSocial, ruc);
    }

    @Override
    public Page<CompanyModel> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CompanyModel getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<CompanyModel> findByRazonSocialContains(String razonSocial, Pageable pageable) {
        return repository.findByRazonSocialContains(razonSocial, pageable);
    }

    @Override
    public CompanyModel findByRuc(String ruc) {
        return repository.findByRuc(ruc);
    }
}
