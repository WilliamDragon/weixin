package com.gjl.weixin.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.gjl.weixin.excel.User;

import java.io.File;
import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/9/11 9:56
 * @Version 1.0
 */
public class TestExcelController {

    public static void main(String[] args) {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        try {
            List<User> userList = ExcelImportUtil.importExcel(new File("D:\\content\\user_list.xlsx"), User.class, params);
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
