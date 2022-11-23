package com.siants.wiki.controller;

import com.siants.wiki.domain.Test;
import com.siants.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Value("${test.hello:TEST}")
    private String testHello;

    @Resource
    private TestService testService;

    /**
     * GET, POST, PUT, DELETE
     *
     * /user?id=1
     * /user/1
     * @return
     */
    /*@PostMapping
    @PutMapping
    @DeleteMapping
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RequestMapping(value = "/hello", method = RequestMethod.DELETE)*/
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post. " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list() {

        return testService.list();
    }

}
