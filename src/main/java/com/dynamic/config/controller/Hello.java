package com.dynamic.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-19
 * Time: 下午8:43
 * 类描述:
 */
@Controller
@WebServlet(asyncSupported = true)
@RequestMapping(value = "/Test/*") //访问的url地址前缀，可以不写，写了就必须在方法url前面先加上class url 进行区分控制器
public class Hello {

    private static Set<AsyncContext> asyncContexts = Collections.synchronizedSet(new HashSet<AsyncContext>());
    private static    int index=0;
    //访问地址：http://localhost:8080/Test/returnSuccess
    @RequestMapping(value = "returnSuccess")    //实际访问的url地址
    public String returnSuccess(HttpServletRequest request) {
        AsyncContext ctx1 =  request.startAsync();
        for (AsyncContext asyncContext : asyncContexts){
            if(asyncContext.equals(ctx1)){
                System.out.println(ctx1+"-----true---");
            }
        }
        asyncContexts.add(ctx1);
        System.out.println(ctx1+"*******");

        return "/WEB-INF/jsp/success";    //返回Views文件夹下的success.jsp页面
    }

    //访问地址：http://localhost:8080/Test/returnString
    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }
}
