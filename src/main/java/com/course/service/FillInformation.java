package com.course.service;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class FillInformation {

    public void fillInformation(){

        String file = FileUtils.readFile("score");//读取
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);//反转
        if(pointObject.getIsFillInformation()==0) {
            //没有填写个人信息
            Integer growScore = pointObject.getGrowScore();
            Integer totalScore = pointObject.getScoreTotal();
            pointObject.setScoreTotal(totalScore + 2);
            pointObject.setGrowScore(growScore + 2);
            //更新，已填写
            pointObject.setIsFillInformation(1);
            String content = JsonUtils.objectToJson(pointObject);
            FileUtils.writeFile("score", content);
        }
        System.out.println("+++++fillInformation积分计算方法执行+++++");
    }

}