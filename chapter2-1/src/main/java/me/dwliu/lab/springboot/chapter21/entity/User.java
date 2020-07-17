package me.dwliu.lab.springboot.chapter21.entity;

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
