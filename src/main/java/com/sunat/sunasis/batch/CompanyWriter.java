package com.sunat.sunasis.batch;

import com.sunat.sunasis.model.CompanyModel;
import com.sunat.sunasis.repository.CompanyRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CompanyWriter implements ItemWriter<CompanyModel>{

    @Autowired
    private CompanyRepository repository;

    @Override
    public void write(List<? extends CompanyModel> companies) throws Exception {
        repository.save(companies);
    }
}
