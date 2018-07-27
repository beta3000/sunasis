package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.security.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, Integer> {

}
