package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IVersionRepository extends JpaRepository<Version, Long> {

    List<Version> getAllByCompleteOrderByNumberDesc(boolean complete);

    List<Version> getAllByDateLessThan(Date date);
}
