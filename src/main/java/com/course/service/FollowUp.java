package com.course.service;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class FollowUp {

    public void followUp(){
    	String file = FileUtils.readFile("score");
    	PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
    	Integer exchangeScore = pointObject.getExchangeScore();
    	Integer total = pointObject.getScoreTotal();
    	pointObject.setExchangeScore(exchangeScore+3);
    	pointObject.setScoreTotal(total+3);
    	String content = JsonUtils.objectToJson(pointObject);
    	FileUtils.writeFile("score", content);
    	
        System.out.println("+++++followUp积分计算方法执行+++++");
    }

}
