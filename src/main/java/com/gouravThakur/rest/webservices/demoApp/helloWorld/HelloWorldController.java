package com.gouravThakur.rest.webservices.demoApp.helloWorld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String HelloWorld(){
        return "Hello-world";

    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean HelloWorldBean(){
        return new HelloWorldBean("hello world");

    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean HelloWorldWithPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world %s",name));

    }

    @GetMapping(path = "/Good-Morning-In-DifferentLanguage")
    public String goodMorningInDifferentLanguage(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }



}
