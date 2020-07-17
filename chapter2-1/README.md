最近 [SpringFox 3.0.0](https://github.com/springfox/springfox/releases/tag/3.0.0) 发布了，距离上一次大版本2.9.2足足有2年多时间了。

之前依赖：

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
    <scope>compile</scope>
</dependency>
```



[程序猿DD](https://github.com/dyc87112) 自制spring boot starter for swagger 2.x，得到了不少Star：

- https://github.com/SpringForAll/spring-boot-starter-swagger



现在SpringFox出了一个starter

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```



来看看这个版本有些什么亮点：

- Spring 5，Webflux 支持（仅请求映射支持，尚不支持功能端点）
- Spring Integration 支持
- Spring Boot 支持 springfox-boot-starter 依赖性（零配置，自动配置支持）
- 具有自动完成功能的文档化配置属性
- 更好的规范兼容性
- 支持 OpenApi 3.0.3
- 几乎零依赖性（唯一需要的库是 spring-plugin、pswagger-core）
- 现有的 swagger2 注释将继续有效，并丰富 open API 3.0 规范



## 项目搭建

- **第一步**：创建一个Spring Boot项目，https://start.spring.io/

- **第二步**：`pom.xml`中添加依赖：

```xml
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-boot-starter</artifactId>
<version>3.0.0</version>
<dependency>
```

- **第三步**：增加注解`@EnableOpenApi`。

```java
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
* swagger 配置
*
* @author liudw
* @date 2020/7/17 21:25
**/
@Configuration
@EnableOpenApi
public class SwaggerConfig {
}
```

**第四步**：配置一些简单的用户接口：

```java
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

```

```java
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户
 *
 * @author liudw
 * @date 2020/7/17 21:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户基本信息")
public class User {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("出生年月")
    private Date birthDate;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("邮箱")
    private String email;

}
```

**第五步**：启动应用！

[访问swagger页面](http://localhost:8080/swagger-ui/index.html)：`http://localhost:8080/swagger-ui/index.html`

![20200717215127-9eBvRX](http://blogimage.dwliu.me/image/20200717215127-9eBvRX.png)

> 注意：
>
> 1. 这次更新，移除了原来默认的swagger页面路径：`http://host/context-path/swagger-ui.html`，新增了两个可访问路径：`http://host/context-path/swagger-ui/index.html`和`http://host/context-path/swagger-ui/`
> 2. 通过调整日志级别，还可以看到新版本的swagger文档接口也有新增，除了以前老版本的文档接口`/v2/api-docs`之外，还多了一个新版本的`/v3/api-docs`接口。