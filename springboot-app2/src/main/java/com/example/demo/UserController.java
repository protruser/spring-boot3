package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate redis;

    @GetMapping("/")
    public String hello() {
        return "<body bgcolor=green>Spring Boot START PAGE</body>";
    }

    @GetMapping("/mysql")
    public String dbTest() {
        try {
            String sql = "select now()";
            String result = jdbcTemplate.queryForObject(sql, String.class);
            return "Database test successful. : " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "Redis SEST OK. key=key, value=100";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "Redis GET OK. key=key >> " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }
}
