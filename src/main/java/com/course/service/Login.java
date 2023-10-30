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
public class Login {

    public void login(){
    	 String file = FileUtils.readFile("score");
         PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
         Integer grow = pointObject.getGrowScore();
         Integer total = pointObject.getScoreTotal();
         Calendar calendar = Calendar.getInstance();
         if(!((calendar.get(Calendar.YEAR)==pointObject.getYear())&&(calendar.get(Calendar.MONTH)==pointObject.getMonth())&&(calendar.get(Calendar.DATE)==pointObject.getDay()))){
             pointObject.setGrowScore(grow+1);
             pointObject.setScoreTotal(total+1);
         }
         pointObject.setYear(calendar.get(Calendar.YEAR));
         pointObject.setMonth(calendar.get(Calendar.MONTH));
         pointObject.setDay(calendar.get(Calendar.DATE));
  
         String content = JsonUtils.objectToJson(pointObject);
         FileUtils.writeFile("score", content);

        System.out.println("+++++login积分计算方法执行+++++");
    }

}
