package com.course.controller;

import org.springframework.stereotype.Component;

/**
 * @author lixuy
 * Created on 2019-04-11
 */
@Component
//成长积分评级
public class GrowPointRating {

    public void growPointRating(){
        System.out.println("======被拦截的growPointRating方法执行======");
    }

}