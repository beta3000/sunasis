package com.sunat.sunasis.repository;

import com.sunat.sunasis.model.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
