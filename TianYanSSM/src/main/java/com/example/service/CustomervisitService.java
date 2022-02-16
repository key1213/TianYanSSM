package com.example.service;

import com.example.entity.Customervisit;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomervisitService {

    List<Customervisit> findAllCustomervisit();

    List<Customervisit> findAllCustomervisitByName(String name);

    Customervisit findCustomervisitById(int id);

    int removeCustomervisit(int id);

    int addCustomervisit(Customervisit customervisit);

    int modifyCustomervisit(Customervisit customervisit);
}
