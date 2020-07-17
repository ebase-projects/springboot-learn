package me.dwliu.lab.springboot.chapter21.controller;

import io.swagger.annotations.*;
import me.dwliu.lab.springboot.chapter21.entity.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 * @author liudw
 * @date 2020/7/17 21:23
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("用户列表")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码，从1开始", paramType = "query", required = true, defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页显示记录数", paramType = "query", required = true, defaultValue = "10", dataType = "int"),
    })
    public List<User> list(@RequestParam int page, @RequestParam int limit) {
        List<User> result = new ArrayList<>();
        result.add(new User("aaa", new Date(), "北京", "aaa@example.com"));
        result.add(new User("bbb", new Date(), "山东", "aaa@example.com"));
        return result;
    }

    @ApiOperation("创建用户")
    @PostMapping
    public User create(@RequestBody User user) {
        return user;
    }

    @ApiOperation("用户详情")
    @GetMapping("/{id}")
    public User findById(@ApiParam("ID") @PathVariable Long id) {
        return new User("bbb", new Date(), "上海", "aaa@example.com");
    }

    @ApiIgnore
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return "delete user : " + id;
    }

}
