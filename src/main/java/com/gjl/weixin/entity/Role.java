package com.gjl.weixin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: WilliamJL
 * @Date: 2019/11/11 17:26
 * @Version 1.0
 */
@Data
public class Role {

    private Long id;

    private String roleName;

    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private Date createDate;
}
