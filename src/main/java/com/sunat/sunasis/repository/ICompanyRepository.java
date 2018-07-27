package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<Company, Long> {

    Company findByRazonSocialOrRuc(String razonSocial, String ruc);

    Page<Company> findAll(Pageable pageable);

    Page<Company> findByRazonSocialContains(String razonSocial, Pageable pageable);

    Company findByRuc(String ruc);

    Company findByRazonSocialIgnoreCase(String razonSocial);

    List<Company> getCompaniesByRucOrRazonSocial(@Param("razonSocial") String razonSocial, @Param("ruc") String ruc);

}
