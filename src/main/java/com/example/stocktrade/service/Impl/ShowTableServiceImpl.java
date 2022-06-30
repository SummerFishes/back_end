package com.example.stocktrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.dao.TradeMapper;
import com.example.stocktrade.service.ShowTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

@Service
public class ShowTableServiceImpl implements ShowTableService {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    TradeMapper tradeMapper;

    @Override
    public JSONObject showTable(String gap) {
        Date endTime = getEndTime();
        Date startTime = getStartTime(endTime, gap);
        System.out.println("startTime"+format.format(startTime));
        System.out.println("endTime"+format.format(endTime));

        //调取的API后续再查
        float exchangeRate = 7;

        List<Map<String, Object>> value = tradeMapper.showTable(startTime, endTime);
        System.out.println("value"+value);
        float totalBuy = 0, totalSell = 0;
        for (Map<String, Object> map : value){
            int clientSide = (int) map.get("clientSide");
            if (clientSide == 0){
                totalBuy += (float) map.get("price") * (int) map.get("size");
            } else {
                totalSell += (float) map.get("price") * (int) map.get("size");
            }
            float notionalPrice =(float) map.get("price") * exchangeRate;
            map.put("notional", String.format("%.2f",notionalPrice));
            map.replace("price",String.format("%.2f",map.get("price")));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", value);
        jsonObject.put("totalBuy", String.format("%.2f", totalBuy));
        jsonObject.put("totalSell", String.format("%.2f", totalSell));
        jsonObject.put("quantity", String.format("%.2f", totalBuy - totalSell));
        jsonObject.put("buyNotional",
                String.format("%.2f", totalBuy * exchangeRate));
        jsonObject.put("sellNotional",
                String.format("%.2f", totalSell * exchangeRate));
        jsonObject.put("netNotional",
                String.format("%.2f", (totalBuy - totalSell) * exchangeRate));


//        List<Map<String, Object>> value = tradeMapper.showTable(startTime, endTime);
//        System.out.println("value"+value);
//        float totalBuy = 0, totalSell = 0;
//        for (Map<String, Object> map : value){
//            int clientSide = (int) map.get("clientSide");
//            float num = (float) map.get("price") * (int) map.get("size");
//            if (clientSide == 0){
//                totalBuy += (float)(Math.round(num*100)/100);
//            } else {
//                totalSell += (float)(Math.round(num*100)/100);
//            }
//            float notionalPrice =(float) map.get("price") * exchangeRate;
//            map.put("notional", (float)(Math.round(notionalPrice*100)/100));
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("value", value);
//        jsonObject.put("totalBuy", totalBuy);
//        jsonObject.put("totalSell", totalSell);
//        jsonObject.put("quantity", totalBuy - totalSell);
//        jsonObject.put("buyNotional",
//                (float)(Math.round(totalBuy * exchangeRate*100)/100));
//        jsonObject.put("sellNotional",
//                (float)(Math.round(totalSell * exchangeRate*100)/100));
//        jsonObject.put("netNotional",
//                (float)(Math.round((totalBuy - totalSell) * exchangeRate)*100)/100);
        return jsonObject;
    }

    public Date getEndTime(){
        Date day=new Date();
        String s = df.format(day);
        Date endTime = null;
        try {
            endTime = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(endTime);
        c.add(Calendar.DATE, 1);
        endTime = c.getTime();
        return endTime;
    }


    public Date getStartTime(Date date, String gap){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        switch (gap){
            case "1D":
                c.add(Calendar.DATE, -1);
                break;
            case "1W":
                c.add(Calendar.DATE, -7);
                break;
            case "2W":
                c.add(Calendar.DATE, -14);
                break;
            case "1M":
                c.add(Calendar.MONTH, -1);
                break;
            case "3M":
                c.add(Calendar.MONTH, -3);
                break;
            case "6M":
                c.add(Calendar.MONTH, -6);
                break;
            case "1Y":
                c.add(Calendar.YEAR, -1);
                break;
            case "YTD":
                break;
            default:
                break;
        }
        Date y = c.getTime();
        return y;
    }
}
