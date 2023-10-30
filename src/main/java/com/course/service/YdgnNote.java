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
public class YdgnNote {

    public void ydgnNote(){
    	
    	String file = FileUtils.readFile("score");
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);        
        Calendar firstTime = Calendar.getInstance();
        Calendar lastTime = pointObject.getCalendar();
        
        if(lastTime == null) {
        	pointObject.setCalendar();
        	lastTime = Calendar.getInstance();
        	Integer grow = pointObject.getGrowScore();
        	pointObject.setGrowScore(grow + 2);
        	Integer total = pointObject.getScoreTotal();
        	pointObject.setScoreTotal(total + 2);
        	
        	Integer ydgnNote = pointObject.getYdgnNote();
        	pointObject.setYdgnNote(ydgnNote + 1);
        }
        
        if(firstTime.get(Calendar.MONTH) - lastTime.get(Calendar.MONTH) == 3 && firstTime.get(Calendar.MONTH) >= lastTime.get(Calendar.MONTH)) {
        	pointObject.setCalendar();
        	Integer grow = pointObject.getGrowScore();
        	pointObject.setGrowScore(grow + 2);
        	Integer total = pointObject.getScoreTotal();
        	pointObject.setScoreTotal(total + 2);
        	
        	Integer ydgnNote = pointObject.getYdgnNote();
        	pointObject.setYdgnNote(ydgnNote + 1);
        	
        }
        String content = JsonUtils.objectToJson(pointObject);
        FileUtils.writeFile("score", content);
    	
        System.out.println("+++++ydgnNote积分计算方法执行+++++");
    }

}
