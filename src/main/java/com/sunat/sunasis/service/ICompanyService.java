package com.sunat.sunasis.service;

import com.sunat.sunasis.model.CompanyModel;

import java.util.List;

public interface ICompanyService {
    void save(CompanyModel company);

    void save(Iterable<CompanyModel> companies);

    void update(CompanyModel company);

    void delete(Long id);

    List<CompanyModel> getAll();

    CompanyModel getById(Long id);
}
