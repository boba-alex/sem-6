package main.dao;

import main.entity.Deal;

import java.util.List;

public interface DealDAO {
    List<Deal> getDeals();
    int countAllDeals();
    int sumAllDeals();
}
