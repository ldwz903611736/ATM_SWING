package com.tyut.atm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author by 李大娃子
 * @classname user
 * @description 用户实体类
 * @date 2020/7/24 9:45
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String password;

    private String age;

    private String sex;

    private String address;

    private String person_id;

    private String phone;

    private String born;

    private String card_id;

    private BigDecimal balance;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", person_id='" + person_id + '\'' +
                ", phone='" + phone + '\'' +
                ", born='" + born + '\'' +
                ", card_id='" + card_id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
