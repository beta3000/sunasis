package com.sunat.sunasis.service;

import com.sunat.sunasis.model.security.Menu;

import java.util.List;

public interface IMenuService {

    void save(Menu menu);

    void update(Menu menu);

    void delete(int idMenu);

    Menu getById(int idMenu);

    List<Menu> getAll();

    List<Menu> getMenuByUser(String nombre);
}
