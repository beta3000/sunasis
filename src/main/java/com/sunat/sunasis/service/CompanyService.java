package com.sunat.sunasis.service;

import com.sunat.sunasis.model.Company;
import com.sunat.sunasis.repository.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository repository;

    @Override
    public Company findByRazonSocialOrRuc(String razonSocial, String ruc) {
        return repository.findByRazonSocialOrRuc(razonSocial, ruc);
    }

    @Override
    public Page<Company> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Company getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<Company> findByRazonSocialContains(String razonSocial, Pageable pageable) {
        return repository.findByRazonSocialContains(razonSocial, pageable);
    }

    @Override
    public Company findByRazonSocialIgnoreCase(String razonSocial) {
        return repository.findByRazonSocialIgnoreCase(razonSocial);
    }

    @Override
    public Company findByRuc(String ruc) {
        return repository.findByRuc(ruc);
    }

    @Override
    public List<Company> getCompaniesByRucOrRazonSocial(String razonSocial, String ruc) {
        return repository.getCompaniesByRucOrRazonSocial(razonSocial, ruc);
    }
}
