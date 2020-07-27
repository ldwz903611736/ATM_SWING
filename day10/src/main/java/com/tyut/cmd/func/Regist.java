package com.tyut.cmd.func;

import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;
import com.tyut.atm.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author by 李大娃子
 * @classname Regist
 * @description TODO
 * @date 2020/7/26 17:03
 */
public class Regist {

    public void reg() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入姓名");
        String username = scanner.next();
        while (!(Pattern.matches("^[\\u4E00-\\u9FA5]{2,4}$", username))) {
            System.err.println("请输入有效用户名！请重新输入！");
            username = scanner.next();
        }
        user.setName(username);

        System.out.println("请输入密码");
        String password = scanner.next();
        while (!(Pattern.matches("^[a-zA-Z]\\w{5,17}$", password))) {
            System.err.println("密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！请重新输入!");
            password = scanner.next();
        }
        user.setPassword(password);

        System.out.println("请选择性别");
        System.out.println("1.男\t2.女");
        boolean sign = true;
        while (sign) {
            Integer sex = scanner.nextInt();
            switch (sex) {
                case 1:
                    user.setSex("男");
                    sign = false;
                    break;
                case 2:
                    user.setSex("nv");
                    sign = false;
                    break;
                default:
                    System.err.println("请选择正确选项！");
                    break;
            }
        }

        System.out.println("请选择年龄");
        String age = scanner.next();
        while (!(Pattern.matches("^(1[89]|[2-8][0-9]|90)$", age))) {
            System.err.println("年龄不符合标准！请重新输入!");
            age = scanner.next();
        }
        user.setAge(age);

        System.out.println("请输入住址");
        String address = scanner.next();
        while (!(StringUtils.isNotBlank(address))) {
            System.err.println("请填写住址！请重新输入!");
            address = scanner.next();
        }
        user.setAddress(address);

        System.out.println("请输入身份证号");
        String id = scanner.next();
        while (!(Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$", id))) {
            System.err.println("请填写正确的身份证号！请重新输入!");
            id = scanner.next();
        }
        user.setPerson_id(id);

        System.out.println("请输入电话");
        String phone = scanner.next();
        while (!(Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", phone))) {
            System.err.println("请填写正确的中国大陆地区手机号！请重新输入!");
            phone = scanner.next();
        }
        user.setPhone(phone);

        System.out.println("请输入出生日期");
        String birth = scanner.next();
        while (!(Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", birth))) {
            System.err.println("请填写正确的中国大陆地区手机号！请重新输入!");
            birth = scanner.next();
        }
        user.setBorn(birth);

        user.setCard_id(Util.getUUID());
        user.setBalance(new BigDecimal("0"));

        System.out.println("注册成功！");

        Service.addUser(user, this.getClass().getClassLoader().getResource("user.txt").getPath());
    }

}
