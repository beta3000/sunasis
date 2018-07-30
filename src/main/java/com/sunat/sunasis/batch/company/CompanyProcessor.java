package com.sunat.sunasis.batch.company;

import com.sunat.sunasis.model.Company;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CompanyProcessor implements ItemProcessor<Company, Company> {
    @Override
    public Company process(Company company) throws Exception {
        System.out.println(company);
        return company;
    }
}
