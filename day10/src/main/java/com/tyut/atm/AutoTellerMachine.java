package com.tyut.atm;

import com.tyut.atm.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author by 李大娃子
 * @classname AutoTellerMachine
 * @description ATM取款系统入口类
 * @date 2020/7/23 9:58
 */
public class AutoTellerMachine extends JFrame implements ActionListener{

    private String regNum;

    /**
     * 初始化界面
     */
    public AutoTellerMachine() {
        regNum = String.valueOf(Service.listUser().size());
        // 主界面
        this.setTitle("ATM机 " + "[当前注册人数:" + regNum + "]");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        // 设置图形界面的位置居于屏幕中央
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2, (screenSize.height / 2 - this.getSize().height / 2));
        this.setResizable(false);

        // 头部欢迎标题
        JPanel titlePad = new JPanel(new BorderLayout());
        titlePad.setBackground(Color.RED);
        JLabel title = new JLabel("欢迎使用图形化界面ATM取款系统！", JLabel.CENTER);
        title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        titlePad.add(title, BorderLayout.CENTER);
        titlePad.setPreferredSize(new Dimension(600,150));
        this.add(titlePad, BorderLayout.NORTH);

        // 登录和注册按钮
        JPanel btnPad = new JPanel(null);
        this.add(btnPad, BorderLayout.CENTER);
        JButton loginBtn = new JButton("登录");
        loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        loginBtn.setBounds(135,62,300,100);
        JButton regBtn = new JButton("注册");
        regBtn.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        btnPad.add(loginBtn);
        regBtn.setBounds(135,220,300,100);
        btnPad.add(regBtn);

        // 添加到监听器
        loginBtn.addActionListener(this);
        regBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if("登录".equals(actionCommand)) {
            new LoginView(this);
        } else if ("注册".equals(actionCommand)) {
            new RegView(this);
        }
    }

    /**
     * 入口函数
     */
    public static void main(String[] args) {
        // 设置UI
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }

        new AutoTellerMachine();
    }
}
