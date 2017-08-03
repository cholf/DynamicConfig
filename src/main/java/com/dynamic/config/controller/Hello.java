package com.dynamic.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
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
    public String returnSuccess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("进入Servlet的时间：" + new Date() + ".");
        out.flush();

        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
        final AsyncContext ctx = req.startAsync();
        ctx.setTimeout(5000);
        new Work(ctx).run();
        out.println("结束Servlet的时间：" + new Date() + ".");
        out.flush();

        return "/WEB-INF/jsp/success";    //返回Views文件夹下的success.jsp页面
    }

    class Work extends Thread {
        private AsyncContext context;

        public Work(AsyncContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);//让线程休眠2s钟模拟超时操作
                PrintWriter wirter = context.getResponse().getWriter();
                wirter.write("延迟输出");
                wirter.flush();
                context.complete();
            } catch (InterruptedException e) {
                System.out.println("sdf");
            } catch (IOException e) {
                System.out.println("45");
            }
        }
    }

        //访问地址：http://localhost:8080/Test/returnString
    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }
}
