## 简介

`Spring Boot`是一种全新的框架，目的是为了简化Spring应用的初始搭建以及开发过程。该框架使用特定的方式(集成starter，约定优于配置)来进行配置，从而使开发人员不需要再定义样板化的配置。SpringBoot提供了一种新的编程范式，可以更加快速便捷地开发Spring项目，在开发过程当中可以专注于应用程序本身的功能开发，而无需在Spring配置上花太大的工夫。

 `Spring Boot`基于Sring4进行设计，继承了原有Spring框架的优秀基因。SpringBoot并不是一个框架，而是一些类库的集合。maven或者gradle项目导入相应依赖即可使用SpringBoot，而无需自行管理这些类库的版本。

Spring Boot 有哪些优点呢？

- 1、为基于Spring的项目开发提供更快捷的入门体验：特别是对于刚开始使用Spring框架的开发人员，无需关心使用Spring框架都需要引入哪些jar包，无需关心Spring框架与其他框架整合时都需要哪些配置文件。SpringBoot会自动配置Spring。

- 2、无需手动管理依赖jar包的版本：SringBoot通过 spring boot starter管理其提供的所有依赖的版本，当升级SpringBoot时，这些依赖的版本也会随之升级，个人无需指定版本号，但是也可以自定义版本号覆盖SpringBoot的默认值。

- 3、自动配置，无需XML：SpringBoot尝试根据你添加的jar依赖自动配置你的应用。

- 4、嵌入式的Tomcat，无需部署war文件。传统的项目我们在启动或者部署的时候，需要将项目的war包放到服务器(如Tomcat、JBoss)的指定目录下，然后再启动服务器。而SpringBoot项目在启动时无需将war文件部署到服务器中。SpringBoot内置了Tomcat和Jetty容器。在服务器启动SpringBoot项目时可以通过jar指令直接启动， 在开发IDE中启动时只需运行Application类的main方法即可。

- 5、简化Maven配置:SpringBoot通过 spring boot starter管理jar包，无需手动配置jar包的版本。之前我们在进行jar包配置时，需要找到jar包的版本号，而在SpringBoot项目中，则无需去管理版本号。

## 快速入门

本文我们将学习如何快速的创建一个Spring Boot应用，并且实现一个简单的Http请求处理。通过这个例子对Spring Boot有一个初步的了解，并体验其结构简单、开发快速的特性。

### 创建基础项目

Spring官方提供了非常方便的工具[Spring Initializr](https://start.spring.io/)来帮助我们创建Spring Boot应用。

#### 使用Spring Initializr页面创建

**第一步**：访问Spring Initializr：https://start.spring.io

![20200718093912-CkE6Ud](http://blogimage.dwliu.me/image/20200718093912-CkE6Ud.png)

如图所示，选项说明如下：

- Project

  使用什么构建工具，Maven还是Gradle；本教程将采用大部分Java人员都熟悉的Maven。

- Language

  使用什么编程语言，Java、Kotlin还是Groovy；本教程将采用Java为主编写。

- Spring Boot

  选用的Spring Boot版本；这里将使用当前最新的`2.3.1`版本。

- Project Metadata
  
  项目的元数据；其实就是Maven项目的基本元素，点开More options可以看到更多设置，根据自己组织的情况输入相关数据，比如：
  
  <img src="http://blogimage.dwliu.me/image/20200718094338-XIwisy.png" alt="20200718094338-XIwisy" style="zoom:50%;" />
  
- Dependencies
  
  选择要加入的Spring Boot组件；本文将实现Web项目，点击右侧 **Add dependencies**,弹窗中输入 **web**关键字，选择第一个即可
  
  <img src="http://blogimage.dwliu.me/image/20200718094721-KdUltk.png" alt="20200718094721-KdUltk" style="zoom:50%;" />
  
  就如下图所示：
  
  ![20200718094925-Pm1upU](http://blogimage.dwliu.me/image/20200718094925-Pm1upU.png)

**第二步**：点击“**Generate**”按钮生成项目；此时浏览器会下载一个与上面`Artifact`名称一样的压缩包（chapter1-1.zip）。

**第三步**：解压项目包，并用编译器以Maven项目导入，以IntelliJ IDEA为例：

- 找到chapter1-1.zip，用解压工具解压
- IDEA 中，File--> Open... ,选择解压后的项目文件夹，点击OK



#### 使用IntelliJ IDEA创建

如果是使用IntelliJ IDEA来写Java程序的话，那么还可以直接在编译器中创建Spring Boot应用。

**第一步**：菜单栏中选择：File --> New --> Project..，我们可以看到如下图所示的创建功能窗口。

![20200718100357-psgaGS](http://blogimage.dwliu.me/image/20200718100357-psgaGS.png)

> 其中Initial Service Url指向的地址就是Spring官方提供的Spring Initializr工具地址，所以这里创建的工程实际上也是基于它的Web API来实现的。
>
> 同时提供一个阿里巴巴的Initial Service Url：https://start.aliyun.com/

**第二步**：点击Next，等待片刻后，我们可以看到如下图所示的工程信息窗口：

![20200718100802-nd4gBL](http://blogimage.dwliu.me/image/20200718100802-nd4gBL.png)

其实内容就跟我们用Web版的Spring Initializr是一模一样的，跟之前在页面上一样填写即可。

**第三步**：继续点击Next，进入选择Spring Boot版本和依赖管理的窗口：

![20200718100856-1f7nGc](http://blogimage.dwliu.me/image/20200718100856-1f7nGc.png)

在这里值的我们关注的是，它不仅包含了Spring Boot Starter POMs中的各个依赖，还包含了Spring Cloud的各种依赖。

**第四步**：点击Next，进入最后关于工程物理存储的一些细节。最后，点击Finish就能完成工程的构建了。

> Intellij中的Spring Initializr虽然还是基于官方Web实现，但是通过工具来进行调用并直接将结果构建到我们的本地文件系统中，让整个构建流程变得更加顺畅，还没有体验过此功能的Spring Boot/Cloud爱好者们不妨可以尝试一下这种不同的构建方式。

### 项目结构解析

![20200718101152-H4IiV4](http://blogimage.dwliu.me/image/20200718101152-H4IiV4.png)

通过上面步骤完成了基础项目的创建。如上图所示，Spring Boot的基础结构共三个文件

- `src/main/java`下的程序入口：`Chapter11Application`
- `src/main/resources`下的配置文件：`application.properties`
- `src/test/`下的测试入口：`Chapter11ApplicationTests`

生成的`Chapter11Application`和`Chapter11ApplicationTests`类都可以直接运行来启动当前创建的项目，由于目前该项目未配合任何数据访问或Web模块，程序会在加载完Spring之后结束运行。

### 项目依赖解析

打开`pom.xml`，一起来看看Spring Boot项目的依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>me.dwliu.lab.springboot</groupId>
    <artifactId>chapter1-1</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>chapter1-1</name>
    <description>第一个spring boot project - helloworld</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

如上所示，主要有四个部分：

- 项目元数据

  创建时候输入的Project Metadata部分，也就是Maven项目的基本元素，包括：groupId、artifactId、version、name、description等

- parent

  继承`spring-boot-starter-parent`的依赖管理，控制版本与打包等内容

- dependencies 项目具体依赖

  `spring-boot-starter-web`用于实现HTTP接口（该依赖中包含了Spring MVC）；

  `spring-boot-starter-test`用于编写单元测试的依赖包。

- build 构建配置部分

  默认使用了`spring-boot-maven-plugin`，配合`spring-boot-starter-parent`就可以把Spring Boot应用打包成JAR来直接运行。

### 编写一个HTTP接口

- 创建package命名为me.dwliu.lab.springboot.chapter11.controller
- 创建`HelloController`类，内容如下：

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

}
```

- 启动主程序，在浏览器中请求：`http://localhost:8080/hello`，可以看到页面返回：Hello World

至此已完成目标，通过Maven构建了一个空白Spring Boot项目，再通过引入web模块实现了一个简单的请求处理。

## 代码示例

本文的相关例子可以查看下面仓库中的`chapter1-1`目录：

- Github：
- Gitee：
