package be.intecbrussel.foodshop.data;

import be.intecbrussel.foodshop.model.Stock;

public interface StockDao {
    Stock readStock();
    void writeStock(Stock stock);
}
