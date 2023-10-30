package com.course.service;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class ExtendedActivity {

    public void extendedActivity(){
        String file = FileUtils.readFile("score");//读取
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);//反转
        Integer exchangeScore = pointObject.getExchangeScore();
        Integer totalScore = pointObject.getScoreTotal();
        pointObject.setScoreTotal(totalScore+5);
        pointObject.setExchangeScore(exchangeScore+5);
        String content = JsonUtils.objectToJson(pointObject);
        FileUtils.writeFile("score", content);
        System.out.println("+++++extendedActivity积分计算方法执行+++++");
    }

}