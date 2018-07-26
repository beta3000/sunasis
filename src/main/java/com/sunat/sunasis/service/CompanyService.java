package com.sunat.sunasis.service;

import com.sunat.sunasis.model.CompanyModel;
import com.sunat.sunasis.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    public void save(CompanyModel company) {
        repository.save(company);
    }

    @Override
    public void save(Iterable<CompanyModel> companies) {
        repository.save(companies);
    }

    @Override
    public void update(CompanyModel company) {
        repository.save(company);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<CompanyModel> getAll() {
        return repository.findAll();
    }

    @Override
    public CompanyModel getById(Long id) {
        return repository.getOne(id);
    }
}
