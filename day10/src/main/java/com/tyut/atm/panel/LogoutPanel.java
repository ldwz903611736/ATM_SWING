package com.tyut.atm.panel;

import com.tyut.atm.AutoTellerMachine;
import com.tyut.atm.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tyut.atm.service.Service.clearInfo;

/**
 * @author by 李大娃子
 * @classname logoutPanel
 * @description 注销界面
 * @date 2020/7/23 16:55
 */
public class LogoutPanel extends JPanel implements ActionListener {

    private MainView mainView;

    public LogoutPanel(MainView mainView) {
        this.mainView = mainView;

        this.setLayout(new GridLayout(2, 1));

        // 构造面板
        JPanel[] jPanels = new JPanel[2];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel();
            this.add(jPanels[i]);
        }

        // 提示信息
        JLabel prompt = new JLabel("你即将注销是否确定?", JLabel.CENTER);
        prompt.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        jPanels[0].setLayout(new BorderLayout());
        jPanels[0].add(prompt, BorderLayout.CENTER);

        //确认按钮
        JButton submitBtn = new JButton("确定注销");
        submitBtn.addActionListener(this);
        submitBtn.setBounds(110,60,200,50);
        submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        jPanels[1].setLayout(null);
        jPanels[1].add(submitBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("确定注销".equals(e.getActionCommand())) {
            // 清空info中的数据
            clearInfo();
            mainView.dispose();
            new AutoTellerMachine();
        }
    }
}
