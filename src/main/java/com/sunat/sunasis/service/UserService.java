package com.sunat.sunasis.service;

import com.sunat.sunasis.model.security.Usuario;
import com.sunat.sunasis.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuario no existe", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getNombre()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);

        return userDetails;
    }
}