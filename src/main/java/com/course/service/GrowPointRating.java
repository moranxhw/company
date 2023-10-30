package com.course.service;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class GrowPointRating {

    public void growPointRating(){
    	String file = FileUtils.readFile("score");
    	PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);
    	Integer grow = pointObject.getGrowScore();
    	String a = "C";
    	if(grow > 10 && grow < 26) {
    		a = "B";
    	}
    	else if(grow > 25) {
    		a = "A";
    	}
    	pointObject.setRate(a);
    	String content = JsonUtils.objectToJson(pointObject);
    	FileUtils.writeFile("score", content);
    	
        System.out.println("+++++growPointRating方法执行+++++");
    }

}
