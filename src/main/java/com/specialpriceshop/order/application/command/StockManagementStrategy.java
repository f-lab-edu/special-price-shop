package com.specialpriceshop.order.application.command;

public interface StockManagementStrategy {

    void decreaseStock(final Long stockId, final Long quantity);

}
