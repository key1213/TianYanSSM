package com.example.service;

import com.example.entity.Customershare;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerShareService {
    List<Customershare> findAllCustomershare();

    List<Customershare> findAll();

    Customershare findCustomershareToInsert(Customershare customershare);
}
