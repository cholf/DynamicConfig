package com.dynamic.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-31
 * Time: 上午10:41
 * 类描述:
 */
@Controller
public class PageController {
    @RequestMapping("/async/test")
    @ResponseBody
    public Callable<String> callable() {
        // 这么做的好处避免web server的连接池被长期占用而引起性能问题，
        // 调用后生成一个非web的服务线程来处理，增加web服务器的吞吐量。
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3 * 1000L);
                return "小单 - " + System.currentTimeMillis();
            }
        };
    }
}
