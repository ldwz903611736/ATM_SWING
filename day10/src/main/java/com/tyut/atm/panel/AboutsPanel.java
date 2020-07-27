package com.tyut.atm.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @author by 李大娃子
 * @classname aboutsPanel
 * @description 关于面板
 * @date 2020/7/23 16:56
 */
public class AboutsPanel extends JPanel {

    public AboutsPanel() {
        this.setLayout(new BorderLayout());
        JPanel titlePad = new JPanel(new BorderLayout());
        this.add(titlePad, BorderLayout.NORTH);
        JLabel title = new JLabel("关于本软件", JLabel.CENTER);
        title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        titlePad.add(title, BorderLayout.CENTER);

        JPanel aboutPad = new JPanel(new BorderLayout());
        this.add(aboutPad, BorderLayout.CENTER);

        JLabel about = new JLabel("<html>欢迎使用ATM模拟系统，本软件使用<br/>Swing和java程序代码写成<br/>作者:李佳峰<br/>2017级软件开发2班4组</html>", JLabel.CENTER);
        about.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        aboutPad.add(about, BorderLayout.CENTER);
    }
}
