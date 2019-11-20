package com.gjl.weixin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: WilliamJL
 * @Date: 2019/11/11 17:28
 * @Version 1.0
 */
@Data
public class Permission {

    private Long id;

    private Long permissType;

    private String permissUrl;
}
