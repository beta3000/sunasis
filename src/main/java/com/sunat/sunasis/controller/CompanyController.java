package com.sunat.sunasis.controller;

import com.sunat.sunasis.model.CompanyModel;
import com.sunat.sunasis.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ICompanyService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyModel> getById(@PathVariable("id") Long id) {
        try {
            CompanyModel model = service.getById(id);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CompanyModel>> getAll(Pageable pageable) {
        try {
            Page<CompanyModel> model = service.getAll(pageable);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/razonSocial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CompanyModel>> searchByRazonSocial(@RequestParam(value = "razonSocial") String razonSocial, Pageable pageable) {
        try {
            Page<CompanyModel> model = service.findByRazonSocialContains(razonSocial, pageable);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ruc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyModel> searchByRuc(@RequestParam(value = "ruc") String ruc) {
        try {
            CompanyModel model = service.findByRuc(ruc);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyModel> search(@RequestParam(value = "razonSocial") String razonSocial, @RequestParam(value = "ruc") String ruc) {
        try {
            CompanyModel model = service.findByRazonSocialOrRuc(razonSocial, ruc);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
