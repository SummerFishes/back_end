package com.example.stocktrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.dao.TradeMapper;
import com.example.stocktrade.service.ShowGraphService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShowGraphServiceImpl implements ShowGraphService {
    @Resource
    TradeMapper tradeMapper;


    @Override
    public JSONObject showGraph(String startTime, String endTime) {
        System.out.println("起始时间：" + startTime + endTime);
        Date startDate = getTime(startTime);
        Date endDate = getTime(endTime);
        List<Map<String, Object>> value = tradeMapper.showGraph(endDate, startDate);
        System.out.println("graph的value"+value);
        //4个List用来存储返回给前端的数据
        List<String> chartDate = new ArrayList<>();
        List<Float> chartBuy = new ArrayList<>();
        List<Float> chartSell = new ArrayList<>();
        List<Float> chartTotal = new ArrayList<>();
        for(Map<String,Object> map : value){
            String date = map.get("date").toString();
            float buy = (float) map.get("buyNum");
            float sell = (float) map.get("sellNum");
            float total = buy - sell;
            chartDate.add(date);
            chartBuy.add(buy);
            chartSell.add(-sell);
            chartTotal.add(total);
        }
        JSONObject result = new JSONObject();
        result.put("chartDate",chartDate);
        result.put("chartBuy",chartBuy);
        result.put("chartSell",chartSell);
        result.put("chartTotal",chartTotal);
        return  result;
    }

    public Date getTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("graph的"+simpleDateFormat.format(date));
        return date;
    }
}