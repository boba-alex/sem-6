package main.services;

import main.dao.DealDAO;
import main.dao.DealDAOImpl;
import main.entity.Deal;

import java.util.List;

public class DealService {
    private DealDAO dealDAO;

    public DealService() {
        this.dealDAO = new DealDAOImpl();
    }

    public List<Deal> getDeals() {
        return dealDAO.getDeals();
    }

    public int countAllDeals() {
        return dealDAO.countAllDeals();
    }

    public int sumAllDeals() {
        return dealDAO.sumAllDeals();
    }

}
