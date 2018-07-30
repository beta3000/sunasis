package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IVersionRepository extends JpaRepository<Version, Long> {

    List<Version> getAllByCompleteOrderByNumberDesc(boolean complete);

    List<Version> getAllByDateLessThan(Date date);
}
