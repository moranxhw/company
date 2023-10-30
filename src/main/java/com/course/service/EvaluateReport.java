package com.course.service;

import com.course.pojo.PointObject;
import com.course.utils.FileUtils;
import com.course.utils.JsonUtils;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
//类名与方法名须与controller层拦截的方法一致
public class EvaluateReport {

    public void evaluateReport(){
        String file = FileUtils.readFile("score");//读取
        PointObject pointObject = JsonUtils.jsonToPojo(file, PointObject.class);//反转
        //填写个人信息，并且填写血糖次数大于等于10
        if(pointObject.getIsFillInformation()!=0&&pointObject.getBloodSugar()>=10){
            //计分
            pointObject.setGrowScore(pointObject.getGrowScore()+2);
            Integer totalScore = pointObject.getScoreTotal();
            pointObject.setScoreTotal(totalScore+2);
        }
        //保存
        String content = JsonUtils.objectToJson(pointObject);
        FileUtils.writeFile("score", content);
        System.out.println("+++++evaluateReport积分计算方法执行+++++");
    }

}