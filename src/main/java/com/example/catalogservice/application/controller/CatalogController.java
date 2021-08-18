package com.example.catalogservice.application.controller;

import com.example.catalogservice.application.domain.CatalogEntity;
import com.example.catalogservice.application.dto.ResponseCatalog;
import com.example.catalogservice.application.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment environment;

    @GetMapping(value = "/health-check")
    public String status() {
        return String.format("It's working in user service "
                + "port(local.server.port) = " + environment.getProperty("local.server.port")
                + "\t port(server.port) = " + environment.getProperty("server.port")
                + "\t \twith token secret = " + environment.getProperty("token.secret")
                + "\t with token time =" + environment.getProperty("token.expiration_time"));
    }

    @GetMapping(value = "/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> allCatalogs = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        allCatalogs.forEach(catalogEntity ->
                result.add(new ModelMapper().map(catalogEntity, ResponseCatalog.class))
        );

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
