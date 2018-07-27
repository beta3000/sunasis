package com.sunat.sunasis.service;

import com.sunat.sunasis.model.security.Menu;
import com.sunat.sunasis.repository.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private IMenuRepository repository;

    @Override
    public void save(Menu menu) {
        repository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        repository.save(menu);
    }

    @Override
    public void delete(int idMenu) {
        repository.delete(idMenu);
    }

    @Override
    public Menu getById(int idMenu) {
        return repository.getOne(idMenu);
    }

    @Override
    public List<Menu> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Menu> getMenuByUser(String nombre) {
        return null;
    }
}
