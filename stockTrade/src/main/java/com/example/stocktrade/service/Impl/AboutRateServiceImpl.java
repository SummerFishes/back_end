package com.example.stocktrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.stocktrade.dao.StockTypeMapper;
import com.example.stocktrade.service.AboutRateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AboutRateServiceImpl implements AboutRateService {
    @Resource
    StockTypeMapper stockTypeMapper;

    LocalDateTime old = LocalDateTime.now().minusHours(1);


    @Override
    public float calculate(String from) {
        System.out.println("calculate" + from);
        clockedUpdate();
        return stockTypeMapper.getRate(from);
    }

    //伪 每小时更新一次汇率
    public void clockedUpdate() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(old, now);
        long hours = duration.toHours();
        if (hours >= 1) {
            old = old.plusHours(hours);
            try {
                updateRate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("汇率更新" + now);
        }
    }

    //更新数据库中的汇率
    public void updateRate() throws Exception {
        String currency[] = {"CNY", "JPY", "EUR", "HKD", "GBP", "SGD", "KRW", "CHF", "RUB", "INR"};
        for (String now : currency) {
            String test = getRate(now);
            JSONObject json_test = JSONObject.parseObject(test);
            JSONObject result = json_test.getJSONObject("result");
            float rate = result.getFloatValue("rate");
            System.out.println("rate" + rate);
            System.out.println("currency" + now);
            stockTypeMapper.updateRate(now, rate);
        }
    }

    //获取汇率
    public String getRate(String currency) {
        URL u = null;
        String result = null;
        try {
            u = new URL("http://api.k780.com/?app=finance.rate&scur=" + currency +
                    "&tcur=USD&appkey=67087&sign=a4891b20701aae6a3ca194e9f9141a9a&format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = u.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while (true) {
                try {
                    if (!((read = in.read(buf)) > 0)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte b[] = out.toByteArray();
        try {
            result = new String(b, "utf-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
