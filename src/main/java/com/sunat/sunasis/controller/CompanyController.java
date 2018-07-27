package com.sunat.sunasis.controller;

import com.sunat.sunasis.model.Company;
import com.sunat.sunasis.service.ICompanyService;
import com.sunat.sunasis.utils.exception.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ICompanyService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> getById(@PathVariable("id") Long id) {
        try {
            Company model = service.getById(id);
            if (model == null) {
                throw new ModelNotFoundException("ID: " + id);
            }
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Company>> getAll(Pageable pageable) {
        try {
            Page<Company> model = service.getAll(pageable);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/razonSocial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Company>> searchByRazonSocial(@RequestParam(value = "razonSocial") String razonSocial, Pageable pageable) {
        try {
            Page<Company> model = service.findByRazonSocialContains(razonSocial, pageable);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ruc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> searchByRuc(@RequestParam(value = "ruc") String ruc) {
        try {
            Company model = service.findByRuc(ruc);
            if (model == null) {
                throw new ModelNotFoundException("RUC: " + ruc);
            }
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> search(@RequestParam(value = "razonSocial") Optional<String> razonSocial, @RequestParam(value = "ruc") Optional<String> ruc) {
        try {
            Company model = null;
            if (razonSocial != null) {
                model = service.findByRazonSocialIgnoreCase(razonSocial.get());
                if (model == null) {
                    throw new ModelNotFoundException("RAZON SOCIAL: " + razonSocial);
                }
            }
            if (ruc != null) {
                model = service.findByRuc(ruc.get());
                if (model == null) {
                    throw new ModelNotFoundException("RUC: " + ruc);
                }
            }
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> search(@RequestParam(value = "razonSocial") Optional<String> razonSocial, @RequestParam(value = "ruc") Optional<String> ruc) {
        try {
            System.out.printf(ruc.get());
            List<Company> models = service.getCompaniesByRucOrRazonSocial(razonSocial.get(), ruc.get());
            return new ResponseEntity<>(models, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
