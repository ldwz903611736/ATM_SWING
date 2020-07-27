package com.tyut.cmd.func;

import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;

import java.util.List;
import java.util.Scanner;

/**
 * @author by 李大娃子
 * @classname Login
 * @description TODO
 * @date 2020/7/26 17:52
 */
public class Login {

    public User log() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        List<User> users = Service.listUser();
        for (User user : users) {
            if (user.getPerson_id().equals(username) && user.getPassword().equals(password)) {
                Service.addUser(user, this.getClass().getClassLoader().getResource("info.txt").getPath());
                System.out.println("登录成功！");
                return user;
            }
        }
        return null;
    }
}
