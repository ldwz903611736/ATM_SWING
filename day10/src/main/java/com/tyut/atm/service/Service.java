package com.tyut.atm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyut.atm.entity.User;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author by 李大娃子
 * @classname Service
 * @description 操控数据的类
 * @date 2020/7/24 10:06
 */
public class Service {

    public static ObjectMapper mapper = new ObjectMapper();

    public static final String USER_FILE_PATH = Service.class.getClassLoader().getResource("user.txt").getPath();

    public static final String INFO_FILE_PATH = Service.class.getClassLoader().getResource("info.txt").getPath();

    /**
     * 添加用户
     * @param user 用户
     * @param filePath 文件地址
     */
    public static void addUser(User user, String filePath) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        try {
            // true表示使用追加的方式写入
            fileWriter = new FileWriter(filePath, true);
            bw = new BufferedWriter(fileWriter);
            // 序列化为一个json对象后存入文件
            bw.write(mapper.writeValueAsString(user));
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取所有用户
     * @return 返回用户集合
     */
    public static List<User> listUser() {
        List<User> userList = new ArrayList<>();
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(USER_FILE_PATH));
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                // 放置出现空行
                if (StringUtils.isNotBlank(line)) {
                    User user = mapper.readValue(line, User.class);
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 根据用户名和密码查抄用户并且录入当前用户文件中
     * @param username 身份证号码
     * @param password 用户密码
     * @return  查找到的用户
     */
    public static User findUser(String username, String password) {
        List<User> userList = listUser();
        for (User user : userList) {
            // 有效用户
            if (user.getPerson_id().equals(username) && user.getPassword().equals(password)) {
                // 存入文件中
                addUser(user, INFO_FILE_PATH);
                // 返回查询到的用户
                return user;
            }
        }
        // 未查找到返回空值
        return null;
    }

    /**
     * 修改金额
     * @param personId  用户身份证号
     * @param money      数额
     * @param flag       true表示存款，false表示取款
     */
    public static void changRemain(String personId, String money, boolean flag) {
        // 1.将修改后的用户存入集合中
        List<User> users = listUser().stream().peek(user -> {
            if (user.getPerson_id().equals(personId)) {
                if (flag) {
                    user.setBalance(user.getBalance().add(new BigDecimal(money)));
                } else {
                    user.setBalance(user.getBalance().subtract(new BigDecimal(money)));
                }
            }
        }).collect(Collectors.toList());

        // 2.清空user.txt文件所有内容,并添加更新后的内容
        modifyUserFile(users);

        // 3.清空info.txt中的所有内容，并添加更新后的内容
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(INFO_FILE_PATH));
            String line = lineNumberReader.readLine();
            User user = mapper.readValue(line, User.class);
            if (flag) {
                user.setBalance(user.getBalance().add(new BigDecimal(money)));
            } else {
                user.setBalance(user.getBalance().subtract(new BigDecimal(money)));
            }

            FileWriter fileWriter = new FileWriter(INFO_FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("");
            bw.flush();
            addUser(user, INFO_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     * @param personId  身份证号
     * @param password   用户密码
     */
    public static void changePassword(String personId, String password) {
        // 根据身份证号在文件中查找并修改目标用户
        List<User> users = listUser().stream().peek(user -> {
            if (personId.equals(user.getPerson_id())) {
                user.setPassword(password);
            }
        }).collect(Collectors.toList());

        // 修改user.txt中的数据
        modifyUserFile(users);
    }

    /**
     * 修改用户文件中的数据
     * @param users        新用户
     */
    public static void modifyUserFile(List<User> users) {
        try {
            FileWriter fileWriter = new FileWriter(USER_FILE_PATH);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("");
            bw.flush();
            for (User user : users) {
                addUser(user, USER_FILE_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空info中的信息
     */
    public static void clearInfo() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(INFO_FILE_PATH));
            bw.write("");
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 用来加载当前登录对象
     * @return 当前登录对象
     */
    public static User loadUser() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(INFO_FILE_PATH));
            String line = lineNumberReader.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(line, User.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断用户的唯一性
     * @param person_id 身份证号
     * @return
     */
    public static boolean uniqueUser(String person_id) {
        List<User> users = listUser();
        for (User user : users) {
            if (user.getPerson_id().equals(person_id)) {
                return false;
            }
        }
        return true;
    }
}

