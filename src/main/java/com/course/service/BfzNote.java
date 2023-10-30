package com.course.service;

import java.util.Calendar;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class BfzNote {

    public void bfzNote(){
    	String file = FileUtils.readFile("score");
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
        Integer grow = pointObject.getGrowScore();
        Integer total = pointObject.getScoreTotal();
        Calendar calendar = Calendar.getInstance();
        if(!(calendar.get(Calendar.YEAR)==pointObject.getBfzYear())){
            pointObject.setGrowScore(grow+3);
            pointObject.setScoreTotal(total+3);
        }
        pointObject.setBfzYear(calendar.get(Calendar.YEAR));

 
        String content = JsonUtils.objectToJson(pointObject);
        FileUtils.writeFile("score", content);
    	
    	
        System.out.println("+++++bfzNote积分计算方法执行+++++");
    }

}
