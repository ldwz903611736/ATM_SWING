package com.tyut.cmd.func;

import com.tyut.atm.entity.User;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author by 李大娃子
 * @classname ChangePwd
 * @description TODO
 * @date 2020/7/26 18:16
 */
public class ChangePwd {

    public void change(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入原始密码");
        String oldPassword = scanner.next();
        while (!(user.getPassword().equals(oldPassword))) {
            System.err.println("密码错误，请重新输入!");
            oldPassword = scanner.next();
        }

        System.out.println("请输入新密码");
        String password = scanner.next();
        while (!(Pattern.matches("^[a-zA-Z]\\w{5,17}$", password))) {
            System.err.println("密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！请重新输入！");
            password = scanner.next();
        }

        System.out.println("请再次输入新密码");
        String passwordAgain = scanner.next();
        while (!(password.equals(passwordAgain))) {
            System.err.println("两次输入的密码不一致，请重新输入！");
            System.out.println("请输入新密码");
            password = scanner.next();
            while (!(Pattern.matches("^[a-zA-Z]\\w{5,17}$", password))) {
                System.err.println("密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！请重新输入！");
                password = scanner.next();
            }
            System.out.println("请再次输入新密码");
            passwordAgain = scanner.next();
        }
        System.out.println("修改成功！");
    }
}
