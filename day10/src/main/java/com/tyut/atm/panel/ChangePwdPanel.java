package com.tyut.atm.panel;

import com.tyut.atm.AutoTellerMachine;
import com.tyut.atm.MainView;
import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import static com.tyut.atm.service.Service.loadUser;

/**
 * @author by 李大娃子
 * @classname changePwdPanel
 * @description 修改密码面板
 * @date 2020/7/23 16:55
 */
public class ChangePwdPanel extends JPanel implements ActionListener {

    private JTextField oldPasswordVal;

    private JTextField passwordVal;

    private JTextField passwordAgainVal;

    private User user;

    private MainView mainView;

    public ChangePwdPanel(MainView mainView) {
        this.mainView = mainView;
        user = loadUser();

        this.setLayout(new GridLayout(4,1));

        JPanel[] jPanels = new JPanel[4];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel(null);
            this.add(jPanels[i]);
        }

        // 添加原密码
        JLabel oldPassword = new JLabel("原密码:");
        oldPassword.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        oldPassword.setBounds(80,35,70,50);
        oldPasswordVal = new JPasswordField();
        oldPasswordVal.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        oldPasswordVal.setBounds(150,40,200,40);
        oldPasswordVal.setTransferHandler(null);
        jPanels[0].add(oldPassword);
        jPanels[0].add(oldPasswordVal);

        // 添加新密码
        JLabel password = new JLabel("新密码:");
        password.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        password.setBounds(80,35,70,50);
        passwordVal = new JPasswordField();
        passwordVal.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordVal.setBounds(150,40,200,40);
        passwordVal.setTransferHandler(null);
        jPanels[1].add(password);
        jPanels[1].add(passwordVal);

        // 添加再次确认
        JLabel passwordAgain = new JLabel("再次输入:");
        passwordAgain.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordAgain.setBounds(60,35,85,50);
        passwordAgainVal = new JPasswordField();
        passwordAgainVal.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        passwordAgainVal.setBounds(150,40,200,40);
        passwordAgainVal.setTransferHandler(null);
        jPanels[2].add(passwordAgain);
        jPanels[2].add(passwordAgainVal);

        // 添加确认按钮
        JButton submitBtn = new JButton("提交");
        submitBtn.addActionListener(this);
        submitBtn.setBounds(115,20,200,40);
        submitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        jPanels[3].add(submitBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("提交".equals(e.getActionCommand())) {
            // 获取参数
            String oldPassword = oldPasswordVal.getText();
            String password = passwordVal.getText();
            String passwordAgain = passwordAgainVal.getText();

            // 输入密码有效性验证
            boolean flag = true;
            if (!(Pattern.matches("^[a-zA-Z]\\w{5,17}$", password) && Pattern.matches("^[a-zA-Z]\\w{5,17}$", passwordAgain))) {
                flag = false;
                JOptionPane.showMessageDialog(this, "密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！", "通知", JOptionPane.ERROR_MESSAGE);
            }
            if (flag) {
                // 首先判断原始密码是否正确
                if (oldPassword.equals(user.getPassword())) {
                    // 判断新密码和再次输入的密码是否相同
                    if (password.equals(passwordAgain)) {
                        // 判断新密码和原始是否相同
                        if (password.equals(oldPassword)) {
                            JOptionPane.showMessageDialog(this, "新密码不能和原密码相同！", "通知", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // 执行修改密码的操作
                            Service.changePassword(user.getPerson_id(), password);
                            // 退出系统，跳转到登录页面
                            mainView.dispose();
                            new AutoTellerMachine();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "两次密码输入不相同！", "通知", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "原始密码错误！", "通知", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
