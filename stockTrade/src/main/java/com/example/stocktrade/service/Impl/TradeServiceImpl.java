//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.stocktrade.service.Impl;

import com.example.stocktrade.dao.DateDataMapper;
import com.example.stocktrade.dao.IssueSectorMapper;
import com.example.stocktrade.dao.OwnerShipMapper;
import com.example.stocktrade.dao.SalePersonMapper;
import com.example.stocktrade.dao.StockMapper;
import com.example.stocktrade.dao.TradeMapper;
import com.example.stocktrade.dao.UserMapper;
import com.example.stocktrade.entity.Trade;
import com.example.stocktrade.feign.StockTradeGetUserId;
import com.example.stocktrade.service.TradeService;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
    @Resource
    private TradeMapper tradeMapper;
    @Resource
    DateDataMapper dateDataMapper;
    @Resource
    IssueSectorMapper issueSectorMapper;
    @Resource
    OwnerShipMapper ownerShipMapper;
    @Resource
    SalePersonMapper salePersonMapper;
    @Resource
    StockMapper stockMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    AboutRateServiceImpl aboutRateService;
    @Autowired
    public StockTradeGetUserId stockTradeGetUserId;

    public TradeServiceImpl() {
    }

    public String trade(String clientName, String ticker, String sector, String salesperson, String ric, String sizeOld, String priceOld, String currency, String hpOld, String flagOld) {
        if (!clientName.equals("") && !ticker.equals("") && !sector.equals("") && !salesperson.equals("") && !ric.equals("") && !sizeOld.equals("") && !priceOld.equals("") && !currency.equals("") && !hpOld.equals("") && !flagOld.equals("")) {
            int size;
            float price;
            try {
                size = Integer.parseInt(sizeOld);
                price = Float.valueOf(priceOld);
            } catch (Exception var31) {
                return "数据错误，size或price非正常数字";
            }

            if (size > 0 && !(price <= 0.0F)) {
                if (price > 100000.0F) {
                    return "price数据存在问题";
                } else {
                    int hp = hpOld.equals("HT") ? 0 : 1;
                    int flag = Integer.parseInt(flagOld);
                    String result = "";
                    int userId = 0;
                    int stockId = 0;
                    int sectorId = 0;
                    int sectorStockId = 0;
                    int salePersonId = 0;

                    try {
                        userId = this.stockTradeGetUserId.searchUser(clientName);
                        stockId = this.searchStockId(ticker, currency, ric);
                        sectorId = this.searchIssueId(sector);
                        sectorStockId = this.searchIssueAndStock(stockId, sectorId);
                        salePersonId = this.searchSalePersonId(salesperson);
                    } catch (Exception var30) {
                        System.out.println(var30);
                    }

                    if (userId == 0) {
                        result = "您输入的客户不存在";
                    } else if (stockId == 0) {
                        result = "您要操作的这支股票不存在";
                    } else if (sectorId == 0) {
                        result = "您输入的机构不存在";
                    } else if (sectorStockId == 0) {
                        result = "您输入的机构不负责该支股票的买卖";
                    } else if (salePersonId == 0) {
                        result = "您输入的销售员不存在";
                    } else {
                        int ownershipId = 0;

                        try {
                            ownershipId = this.searchOwnId(userId, stockId);
                        } catch (Exception var29) {
                            System.out.println(var29);
                        }

                        int ownNum = 0;
                        if (ownershipId == 0) {
                            this.addOwn(userId, stockId);
                        } else {
                            ownNum = this.searchOwnNum(userId, stockId);
                        }

                        int sumNum = this.searchSumNum(stockId);
                        float exchangeRate = this.aboutRateService.calculate(currency);
                        if (flag == 0) {
                            Map<String, Integer> stockTemp = this.searchStockLimit(stockId);
                            System.out.println(stockTemp);
                            int stockLimit = stockTemp.get("stockLimit");
                            int releaseNum = stockTemp.get("releaseNum");
                            if (size + ownNum > stockLimit) {
                                result = "您持有的该支股票将超出限额，购买失败";
                            } else if (size + sumNum > releaseNum) {
                                result = "您要购买的股票大于该支股票剩余量，无法购买";
                            } else if (this.addTrade(userId, stockId, salePersonId, size, flag, hp, price)) {
                                result = "购买成功";
                                String str = this.updateDateData((float)size * price * exchangeRate, 0.0F);
                                System.out.println(str);
                                this.updateOwn(size, ownershipId);
                            } else {
                                System.out.println("buy操作系统故障：" + result);
                            }
                        } else if (size > ownNum) {
                            result = "您未持有此数量的股票";
                        } else if (this.addTrade(userId, stockId, salePersonId, size, flag, hp, price)) {
                            result = "售卖成功";
                            String str = this.updateDateData(0.0F, (float)size * price * exchangeRate);
                            System.out.println(str);
                            this.updateOwn(-size, ownershipId);
                        } else {
                            System.out.println("sell操作系统故障：" + result);
                        }
                    }

                    return result;
                }
            } else {
                return "数据错误，不能为零或负数";
            }
        } else {
            return "数据为空！";
        }
    }

    public boolean addTrade(int userId, int stockId, int salePersonId, int tradeSize, int tradeType, int tradeMethod, float tradePerPrice) {
        Trade trade = new Trade(0, userId, stockId, salePersonId, tradeSize, null, null, tradeType, tradeMethod, tradePerPrice);
        return this.tradeMapper.add(trade);
    }

    public String updateDateData(float buyNum, float sellNum) {
        int dateDataId = 0;

        try {
            dateDataId = this.dateDataMapper.searchId();
            System.out.println("id: " + dateDataId);
        } catch (Exception var5) {
            System.out.println(var5);
        }

        String str = "对date_data表的操作成功";
        if (dateDataId == 0) {
            if (!this.dateDataMapper.add()) {
                str = "添加数据到date_data表时出错";
            }

            dateDataId = this.dateDataMapper.searchId();
        }

        if (buyNum > 0.0F) {
            System.out.println(dateDataId);
            if (!this.dateDataMapper.updateBuy(buyNum, dateDataId)) {
                str = "更新date_data表的buy_num时出错";
            }
        }

        if (sellNum > 0.0F && !this.dateDataMapper.updateSell(sellNum, dateDataId)) {
            str = "更新date_data表的sell_num时出错";
        }

        return str;
    }

    public int searchIssueId(String issueSectorName) {
        return this.issueSectorMapper.searchId(issueSectorName);
    }

    public int searchOwnNum(int userId, int stockId) {
        return this.ownerShipMapper.searchOwnNum(userId, stockId);
    }

    public int searchSumNum(int stockId) {
        return this.ownerShipMapper.searchSumNum(stockId);
    }

    public int searchOwnId(int userId, int stockId) {
        return this.ownerShipMapper.searchId(userId, stockId);
    }

    public boolean updateOwn(int num, int ownershipId) {
        return this.ownerShipMapper.update(num, ownershipId);
    }

    public boolean addOwn(int userId, int stockId) {
        return this.ownerShipMapper.add(userId, stockId);
    }

    public int searchSalePersonId(String personName) {
        return this.salePersonMapper.searchId(personName);
    }

    public int searchStockId(String stockName, String stockType, String ric) {
        return this.stockMapper.searchId(stockName, stockType, ric);
    }

    public int searchIssueAndStock(int stockId, int sectorId) {
        return this.stockMapper.searchIssueAndStock(stockId, sectorId);
    }

    public Map<String, Integer> searchStockLimit(int stockId) {
        return this.stockMapper.searchLimitAndRelease(stockId);
    }

    public int searchUserId(String username) {
        return this.userMapper.searchId(username);
    }
}
