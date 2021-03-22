package com.demo.api;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/allRequestParams")
    public Map<String, String> allRequestParams(@RequestParam(required = false) Map<String, String> allParams) {
        return allParams;
    }

    @GetMapping("/multiValueParams")
    public Map<String, List<String>> multiValueParams(@RequestParam(required = false) MultiValueMap<String, String> allParams) {
        return allParams;
    }

    //curl http://localhost:8081/api/hello/world
    //{"p1":"hello","p2":"world"}
    @GetMapping("/{p1}/{p2}")
    public Map<String, String> pathVariables(@PathVariable Map<String, String> pathVariables) {
        return pathVariables;
    }

    //curl -d 'name=admin&age=20' http://localhost:8081/api/pojo
    //{"name":"admin","age":20}
    @PostMapping("/pojo")
    public User pojo(User user) {
        return user;
    }
}
