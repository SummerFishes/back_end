package com.example.stocktrade.service.Impl;

import com.example.stocktrade.dao.*;
import com.example.stocktrade.entity.Trade;
import com.example.stocktrade.service.TradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

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

    @Override
    public String trade(String clientName, String ticker, String sector, String salesperson,
                        String ric, String sizeOld, String priceOld, String currency, String hpOld,
                        String flagOld) {
        //保证数据不为空,会报空指针异常
        if(clientName.equals("") || ticker.equals("") || sector.equals("") || salesperson.equals("")
        || ric.equals("") || sizeOld.equals("") || priceOld.equals("") || currency.equals("")
        || hpOld.equals("") || flagOld.equals("")){
            return "存在空数据！";
        }
        //若不为空，进行数据转换
        int size = Integer.parseInt(sizeOld);
        float price = Float.valueOf(priceOld);
        int hp = hpOld.equals("HT") ? 0 : 1;
        int flag = Integer.parseInt(flagOld);

        String result = "";
        //首先判断用户，股票，机构，销售员是否存在
        int user_id = 0, stock_id = 0, sale_person_id = 0;
        try {
            user_id = searchUserId(clientName);
            stock_id = searchStockId(ticker);
            sale_person_id = searchSalePersonId(salesperson);
        } catch (Exception e){
            ;
        }
        if(user_id == 0)  result = "您输入的客户不存在";
        else if(stock_id == 0) result = "您要操作的这支股票不存在";
        else if(searchIssueId(sector) == null) result = "您输入的机构不存在";
        else if(sale_person_id == 0) result = "您输入的销售员不存在";
        //然后判断操作是否成功
        else{
            int ownership_id = 0;
            try {
                ownership_id  = searchOwnId(user_id, stock_id);
            } catch (Exception e){
            }
            int ownNum = 0;
            if (ownership_id  == 0){
                //若操作未成功也会产生一行新数据在ownership表
                addOwn(user_id, stock_id);
            }else{
                ownNum = searchOwnNum(user_id, stock_id);
            }

            int sumNum = searchSumNum(stock_id);
            //如果是buy操作
            if(flag == 0){
                Map<String, Integer> stock_temp = searchStockLimit(stock_id);
                int stock_limit = stock_temp.get("stock_limit");
                int release_num = stock_temp.get("release_num");
                if (size + ownNum > stock_limit){
                    result = "您持有的该支股票将超出限额，购买失败";
                } else if (size + sumNum > release_num){
                    result = "您要购买的股票大于该支股票剩余量，无法购买";
                } else {
                    if(addTrade(user_id,stock_id,sale_person_id,size,flag,hp,price)){
                        result = "购买成功";
                        String str = updateDateData(size*price,0);
                        System.out.println(str);
                        //如果是卖，则填size的负数
                        updateOwn(size, ownership_id);
                    }else{
                        System.out.println("buy操作系统故障："+result);
                    }
                }
            //sell操作
            } else {
                if (size > ownNum){
                    result = "您未持有此数量的股票";
                } else if (addTrade(user_id,stock_id,sale_person_id,size,flag,hp,price)){
                    result = "售卖成功";
                    String str = updateDateData(0,size*price);
                    System.out.println(str);
                    //如果是卖，则填size的负数
                    updateOwn(-size, ownership_id);
                } else {
                    System.out.println("sell操作系统故障："+result);
                }
            }
        }
        return result;
    }

    public boolean addTrade(int user_id, int stock_id, int sale_person_id, int trade_size, int trade_type, int trade_method, float trade_per_price) {
        Trade trade = new Trade(0,user_id,stock_id,sale_person_id,trade_size,null,null,trade_type,trade_method,trade_per_price);
        return tradeMapper.add(trade);
    }

    public String updateDateData(float buy_num, float sell_num) {
        int date_date_id = 0;
        try {
            date_date_id = dateDataMapper.searchId();
            System.out.println("id: "+date_date_id);
        } catch (Exception e){

        }
        String str = "对date_data表的操作成功";
        if (date_date_id == 0){
            if(!dateDataMapper.add()){
                str = "添加数据到date_data表时出错";
            }
            date_date_id = dateDataMapper.searchId();
        }

        if (buy_num > 0){
            System.out.println(date_date_id);
            if(!dateDataMapper.updateBuy(buy_num, date_date_id)){
                str = "更新date_data表的buy_num时出错";
            }
        }
        if (sell_num > 0){
            if(!dateDataMapper.updateSell(sell_num, date_date_id)){
                str = "更新date_data表的sell_num时出错";
            }
        }
        return str;
    }

    public String searchIssueId(String issue_sector_name) {
        return issueSectorMapper.searchId(issue_sector_name);
    }

    public int searchOwnNum(int user_id, int stock_id) {
        return ownerShipMapper.searchOwnNum(user_id, stock_id);
    }

    public int searchSumNum(int stock_id) {
        return ownerShipMapper.searchSumNum(stock_id);
    }

    public int searchOwnId(int user_id, int stock_id) {
        return ownerShipMapper.searchId(user_id, stock_id);
    }

    public boolean updateOwn(int num, int ownership_id) {
        return ownerShipMapper.update(num, ownership_id);
    }

    public boolean addOwn(int user_id, int stock_id) {
        return ownerShipMapper.add(user_id, stock_id);
    }

    public int searchSalePersonId(String person_name) {
        return salePersonMapper.searchId(person_name);
    }

    public int searchStockId(String stock_name) {
        return stockMapper.searchId(stock_name);
    }

    public Map<String, Integer> searchStockLimit(int stock_id) {
        return stockMapper.searchLimitAndRelease(stock_id);
    }

    public int searchUserId(String username) {
        return userMapper.searchId(username);
    }
}
