//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.stocktrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.dao.TradeMapper;
import com.example.stocktrade.service.ShowTableService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShowTableServiceImpl implements ShowTableService {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    AboutRateServiceImpl aboutRateService = new AboutRateServiceImpl();
    @Resource
    TradeMapper tradeMapper;

    public JSONObject showTable(String gap, String type, String sortType) {
        Date endTime = this.getEndTime();
        Date startTime = this.getStartTime(endTime, gap);
        System.out.println("table的startTime" + this.format.format(startTime));
        System.out.println("table的endTime" + this.format.format(endTime));
        new ArrayList();
        System.out.println("table:" + sortType);
        ArrayList value;
        if (sortType.equals("asc")) {
            value = this.tradeMapper.showTable1(startTime, endTime, type);
        } else {
            value = this.tradeMapper.showTable2(startTime, endTime, type);
        }

        System.out.println("table的value" + value);
        int totalBuy = 0;
        int totalSell = 0;
        float totalBuyNation = 0.0F;
        float totalSellNation = 0.0F;
        Iterator var11 = value.iterator();

        while(var11.hasNext()) {
            Map<String, Object> map = (Map)var11.next();
            int clientSide = (Integer)map.get("clientSide");
            int hp = (Integer)map.get("hp");
            float price = (Float)map.get("price");
            int size = (Integer)map.get("size");
            String currency = (String)map.get("currency");
            System.out.println("currency" + currency);
            float exchangeRate = this.aboutRateService.calculate(currency);
            if (clientSide == 0) {
                map.put("clientSide", "buy");
                totalBuy += size;
                totalBuyNation += (float)size * price * exchangeRate;
            } else {
                map.put("clientSide", "sell");
                totalSell += size;
                totalSellNation += (float)size * price * exchangeRate;
            }

            if (hp == 0) {
                map.put("hp", "HT");
            } else {
                map.put("hp", "PT");
            }

            float notionalPrice = price * exchangeRate * (float)size;
            map.put("notional", Float.valueOf(String.format("%.2f", notionalPrice)));
            map.replace("price", Float.valueOf(String.format("%.2f", price)));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", value);
        jsonObject.put("totalBuy", totalBuy);
        jsonObject.put("totalSell", totalSell);
        jsonObject.put("quantity", totalBuy - totalSell);
        jsonObject.put("buyNotional", Float.valueOf(String.format("%.2f", totalBuyNation)));
        jsonObject.put("sellNotional", Float.valueOf(String.format("%.2f", totalSellNation)));
        jsonObject.put("netNotional", Float.valueOf(String.format("%.2f", totalBuyNation - totalSellNation)));
        jsonObject.put("records", value.size());
        return jsonObject;
    }

    public Date getEndTime() {
        Date day = new Date();
        String s = this.df.format(day);
        Date endTime = null;

        try {
            endTime = this.df.parse(s);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(endTime);
        c.add(5, 1);
        endTime = c.getTime();
        return endTime;
    }

    public Date getStartTime(Date date, String gap) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        byte var5 = -1;
        switch(gap.hashCode()) {
            case 1587:
                if (gap.equals("1D")) {
                    var5 = 0;
                }
                break;
            case 1596:
                if (gap.equals("1M")) {
                    var5 = 3;
                }
                break;
            case 1606:
                if (gap.equals("1W")) {
                    var5 = 1;
                }
                break;
            case 1608:
                if (gap.equals("1Y")) {
                    var5 = 6;
                }
                break;
            case 1637:
                if (gap.equals("2W")) {
                    var5 = 2;
                }
                break;
            case 1658:
                if (gap.equals("3M")) {
                    var5 = 4;
                }
                break;
            case 1751:
                if (gap.equals("6M")) {
                    var5 = 5;
                }
                break;
            case 88201:
                if (gap.equals("YTD")) {
                    var5 = 7;
                }
        }

        switch(var5) {
            case 0:
                c.add(5, -1);
                break;
            case 1:
                c.add(5, -7);
                break;
            case 2:
                c.add(5, -14);
                break;
            case 3:
                c.add(2, -1);
                break;
            case 4:
                c.add(2, -3);
                break;
            case 5:
                c.add(2, -6);
                break;
            case 6:
                c.add(1, -1);
                break;
            case 7:
                c.add(1, -10);
        }

        Date y = c.getTime();
        return y;
    }
}
