package com.sunat.sunasis.batch.company;

import com.sunat.sunasis.model.Company;
import com.sunat.sunasis.model.Version;
import com.sunat.sunasis.repository.ICompanyRepository;
import com.sunat.sunasis.repository.IVersionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CompanyWriter implements ItemWriter<Company> {

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public void write(List<? extends Company> companies) throws Exception {
        System.out.println(companies);
        companyRepository.save(companies);
    }
}
