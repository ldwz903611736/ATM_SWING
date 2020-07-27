package com.tyut.atm;

import com.tyut.atm.listener.MyWindowListener;
import com.tyut.atm.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tyut.atm.service.Service.clearInfo;
import static com.tyut.atm.service.Service.loadUser;

/**
 * @author by 李大娃子
 * @classname MainView
 * @description 用户主页面
 * @date 2020/7/23 16:07
 */
public class MainView extends JFrame implements ActionListener {

    private JPanel contentPad;

    private String username;

    private Timer time;

    /**
     * 初始化界面
     */
    public MainView() {
        username = loadUser().getName();

        this.setTitle("ATM取款系统 [" + "当前用户:" + username + "]");
        this.setSize(600,590);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 添加窗体监听器
        this.addWindowListener(new MyWindowListener());
        this.setResizable(false);

        // 顶部欢迎面部
        JPanel titlePad = new JPanel(new BorderLayout());
        titlePad.add(getTimelabel());
        titlePad.setBackground(Color.RED);
        JLabel title = new JLabel("欢迎使用图形化界面ATM取款系统！", JLabel.CENTER);
        title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        titlePad.add(title, BorderLayout.CENTER);
        titlePad.setPreferredSize(new Dimension(600,100));
        this.add(titlePad, BorderLayout.NORTH);

        // 中部主体面板，用来整体管理菜单栏面板和内容面板
        JPanel mainPad = new JPanel(new BorderLayout());
        this.add(mainPad, BorderLayout.CENTER);

        // 左边菜单栏
        JPanel listPad = new JPanel(new GridLayout(7, 1));
        listPad.setPreferredSize(new Dimension(150,490));
        mainPad.add(listPad, BorderLayout.WEST);

        // 左边菜单项
        JButton infoBtn = new JButton("用户信息");
        infoBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton withDrawalsBtn = new JButton("取款");
        withDrawalsBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton depositBtn = new JButton("存款");
        depositBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton changePwdBtn = new JButton("修改密码");
        changePwdBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton logoutBtn = new JButton("注销");
        logoutBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton aboutsBtn = new JButton("关于");
        aboutsBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        JButton quitBtn = new JButton("退出");
        quitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 17));

        // 将每个按钮(菜单项)添加到菜单栏中
        listPad.add(infoBtn);
        listPad.add(withDrawalsBtn);
        listPad.add(depositBtn);
        listPad.add(changePwdBtn);
        listPad.add(logoutBtn);
        listPad.add(aboutsBtn);
        listPad.add(quitBtn);

        // 给每个菜单项注册监听器
        infoBtn.addActionListener(this);
        withDrawalsBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        changePwdBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        aboutsBtn.addActionListener(this);
        quitBtn.addActionListener(this);

        // 右侧内容框
        contentPad = new JPanel(new GridLayout(1,1));
        mainPad.add(contentPad, BorderLayout.CENTER);

        // 设置窗体可见
        this.setVisible(true);
    }

    private JLabel getTimelabel() {
            JLabel timelabel = new JLabel("");
            timelabel.setBounds(5, 65, 200, 20);
            timelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
            timelabel.setForeground(new Color(182, 229, 248));
            time = new Timer(1000,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    timelabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
                }
            });
            time.start();
        return timelabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("用户信息".equals(actionCommand)) {
            showSpecifiedPanel(new InfoPanel());
        } else if ("取款".equals(actionCommand)) {
            showSpecifiedPanel(new WithDrawalsPanel(this));
        } else if ("存款".equals(actionCommand)) {
            showSpecifiedPanel(new DepositPanel(this));
        } else if ("修改密码".equals(actionCommand)) {
            showSpecifiedPanel(new ChangePwdPanel(this));
        } else if ("注销".equals(actionCommand)) {
            showSpecifiedPanel(new LogoutPanel(this));
        } else if ("关于".equals(actionCommand)) {
            showSpecifiedPanel(new AboutsPanel());
        } else if ("退出".equals(actionCommand)) {
            clearInfo();
            this.dispose();
        }


    }

    /**
     * 以contentPad为底，然后覆盖所需要的Jpanle，实现内容的切换
     * @param showPanel 内容面板，根据事件的不同切换
     */
    public void showSpecifiedPanel(JPanel showPanel) {
        contentPad.removeAll();
        contentPad.add(showPanel);
        contentPad.validate();
        contentPad.repaint();
    }
}
