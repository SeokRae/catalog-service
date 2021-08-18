package com.example.catalogservice.application.service;

import com.example.catalogservice.application.domain.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
