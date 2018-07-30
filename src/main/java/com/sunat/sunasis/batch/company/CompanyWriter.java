package com.sunat.sunasis.batch.company;

import com.sunat.sunasis.model.Company;
import com.sunat.sunasis.model.Version;
import com.sunat.sunasis.repository.ICompanyRepository;
import com.sunat.sunasis.repository.IVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
public class CompanyWriter implements ItemWriter<Company> {

    public final static Logger logger = LoggerFactory.getLogger(CompanyWriter.class);

    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public void write(List<? extends Company> companies) throws  Exception{
                  List<Company> all = (List<Company>) companies;
            logger.info("Runtime: companies saving ..." + all.size());
           companyRepository.save(all);
    }
}
