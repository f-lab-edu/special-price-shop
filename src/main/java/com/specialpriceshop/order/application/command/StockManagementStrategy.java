package com.specialpriceshop.order.application.command;

public interface StockManagementStrategy {

    int decreaseStock(final Long stockId, final Long quantity);

}
