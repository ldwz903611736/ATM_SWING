package com.tyut.atm;

import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author by 李大娃子
 * @classname LoginView
 * @description 登录页面
 * @date 2020/7/23 14:17
 */
public class LoginView extends JFrame implements ActionListener {

    private JTextField jtUsername;

    private JTextField jtPassword;

    private AutoTellerMachine atm;

    /**
     * 初始化登录页面的同时，将主页面注入其中，方便后续操作
     * @param atm 入口页面对象
     */
    public LoginView(AutoTellerMachine atm) {
        // 注入主页面(ATM)
        this.atm = atm;

        this.setTitle("登录");
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel jPanel = new JPanel(null);
        this.add(jPanel, BorderLayout.CENTER);

        // 登录按钮
        JButton logBtn = new JButton("登录");
        logBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        logBtn.addActionListener(this);
        logBtn.setBounds(140,125,100,50);

        // 用户名
        JLabel jlUsername = new JLabel("用户名:");
        jlUsername.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlUsername.setBounds(60,20,60,50);
        jtUsername = new JTextField();
        jtUsername.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtUsername.setBounds(125,30,200,35);
        jtUsername.setTransferHandler(null);

        // 密码
        JLabel jlPassword = new JLabel("密码:");
        jlPassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlPassword.setBounds(78,70,60,50);
        jtPassword = new JPasswordField();
        jtPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtPassword.setBounds(125,80,200,35);
        jtPassword.setTransferHandler(null);

        // 将组件添加到面板中
        jPanel.add(logBtn);
        jPanel.add(jlUsername);
        jPanel.add(jlPassword);
        jPanel.add(jtUsername);
        jPanel.add(jtPassword);

        // 设置窗体可见
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("登录".equals(e.getActionCommand())) {
            // 先判断唯一性
            User user = Service.findUser(jtUsername.getText(), jtPassword.getText());
            if (user != null) {
                atm.dispose();
                this.dispose();
                new MainView();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！", "通知", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
