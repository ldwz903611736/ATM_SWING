package com.tyut.atm.panel;

import com.tyut.atm.entity.User;

import javax.swing.*;
import java.awt.*;

import static com.tyut.atm.service.Service.loadUser;

/**
 * @author by 李大娃子
 * @classname panel.InfoPanel
 * @description 个人信息面板
 * @date 2020/7/23 16:52
 */
public class InfoPanel extends JPanel {

    public User user;

    public InfoPanel() {
        user = loadUser();
        this.setLayout(new GridLayout(6,1));

        // 构造面板
        JPanel[] jPanels = new JPanel[6];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel(null);
            this.add(jPanels[i]);
        }

        // 添加姓名和卡号列
        JLabel username = new JLabel("姓名:");
        username.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        username.setBounds(30,12,40,50);
        JLabel usernameVal = new JLabel(user.getName());
        usernameVal.setBounds(70,12,60,50);
        usernameVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        JLabel card = new JLabel("卡号:");
        card.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        card.setBounds(190,12,40,50);
        JLabel cardVal = new JLabel(user.getCard_id());
        cardVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        cardVal.setBounds(230,12,200,50);

        // 添加性别和年龄字段
        JLabel sex = new JLabel("性别:");
        sex.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        sex.setBounds(30,12,40,50);
        JLabel sexVal = new JLabel(user.getSex());
        sexVal.setBounds(70,12,60,50);
        sexVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        JLabel age = new JLabel("年龄:");
        age.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        age.setBounds(190,12,40,50);
        JLabel ageVal = new JLabel(user.getAge());
        ageVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        ageVal.setBounds(230,12,200,50);

        // 添加出生和电话字段
        JLabel born = new JLabel("出生:");
        born.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        born.setBounds(30,12,40,50);
        JLabel bornVal = new JLabel(user.getBorn());
        bornVal.setBounds(70,12,110,50);
        bornVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        JLabel phone = new JLabel("电话:");
        phone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        phone.setBounds(190,12,40,50);
        JLabel phoneVal = new JLabel(user.getPhone());
        phoneVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        phoneVal.setBounds(230,12,200,50);

        // 添加住址字段
        JLabel address = new JLabel("住址:");
        address.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        address.setBounds(30,12,40,50);
        JLabel addressVal = new JLabel(user.getAddress());
        addressVal.setBounds(70,12,110,50);
        addressVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        // 添加身份证号字段
        JLabel psersonId = new JLabel("身份证:");
        psersonId.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        psersonId.setBounds(30,12,60,50);
        JLabel psersonIdVal = new JLabel(user.getPerson_id());
        psersonIdVal.setBounds(90,12,200,50);
        psersonIdVal.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        // 创建余额字段
        JLabel balance = new JLabel("余额:" + user.getBalance(), JLabel.CENTER);
        balance.setFont(new Font("微软雅黑", Font.PLAIN, 25));

        // 统一将各字段添加到面板总
        jPanels[0].add(username);
        jPanels[0].add(usernameVal);
        jPanels[0].add(card);
        jPanels[0].add(cardVal);
        jPanels[1].add(age);
        jPanels[1].add(ageVal);
        jPanels[1].add(sex);
        jPanels[1].add(sexVal);
        jPanels[2].add(born);
        jPanels[2].add(bornVal);
        jPanels[2].add(phone);
        jPanels[2].add(phoneVal);
        jPanels[3].add(address);
        jPanels[3].add(addressVal);
        jPanels[4].add(psersonId);
        jPanels[4].add(psersonIdVal);
        jPanels[5].setLayout(new BorderLayout());
        jPanels[5].add(balance, BorderLayout.CENTER);
    }

}
