package com.example.stocktrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.dao.DateDataMapper;
import com.example.stocktrade.service.ShowGraphService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShowGraphServiceImpl implements ShowGraphService {
    @Resource
    DateDataMapper dateDataMapper;


    @Override
    public JSONObject showGraph(String startTime, String endTime) {
        System.out.println("启动了showGraph");
        System.out.println("startTime" + startTime);
        System.out.println("endTime" + endTime);
        Date startDate = getTime(startTime);
        Date endDate = getTime(endTime);
        System.out.println("startDate" + startDate);
        System.out.println("endDate" + endDate);
        List<Map<String, Object>> value = dateDataMapper.showGraph(startDate, endDate);
        System.out.println("graph的value" + value);
        //4个List用来存储返回给前端的数据
        List<String> chartDate = new ArrayList<>();
        List<Float> chartBuy = new ArrayList<>();
        List<Float> chartSell = new ArrayList<>();
        List<Float> chartTotal = new ArrayList<>();
        for (Map<String, Object> map : value) {
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
        result.put("chartDate", chartDate);
        result.put("chartBuy", chartBuy);
        result.put("chartSell", chartSell);
        result.put("chartTotal", chartTotal);
        System.out.println("result" + result.toString());
        return result;
    }

    public Date getTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("graph的" + simpleDateFormat.format(date));
        return date;
    }
}