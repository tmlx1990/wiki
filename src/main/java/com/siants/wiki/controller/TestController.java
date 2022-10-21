package com.siants.wiki.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

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
        return "Hello World!";
    }
}
