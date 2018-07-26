package com.sunat.sunasis.batch;

import com.sunat.sunasis.model.CompanyModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CompanyProcessor implements ItemProcessor<CompanyModel, CompanyModel> {
    @Override
    public CompanyModel process(CompanyModel company) throws Exception {
        return company;
    }
}
