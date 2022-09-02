package com.example.stocktrade.service;

/**
 * 该层为所有与buy与sell操作有关的借口
 * 最终只需调用trade函数就好
 */
public interface TradeService {

    public String trade(String clientName, String ticker, String sector, String salesperson,
                        String ric, String size, String price, String currency, String hp,
                        String flag);
}
