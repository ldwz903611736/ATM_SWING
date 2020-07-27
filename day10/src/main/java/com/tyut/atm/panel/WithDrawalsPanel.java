package com.tyut.atm.panel;
import com.tyut.atm.MainView;
import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import static com.tyut.atm.service.Service.loadUser;

/**
 * @author by 李大娃子
 * @classname withDrawalsPanel
 * @description 取款面板
 * @date 2020/7/23 16:55
 */
public class WithDrawalsPanel extends JPanel implements ActionListener {

    private User user;

    private JTextField input;

    private MainView mainView;

    public WithDrawalsPanel(MainView mainView) {
        // 注入主界面
        this.mainView = mainView;
        // 初始化当前用户
        user = loadUser();

        this.setLayout(new GridLayout(4, 1));

        // 构造四个面板
        JPanel[] jPanels = new JPanel[4];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel(new BorderLayout());
            this.add(jPanels[i]);
        }

        // 请输入取款余额
        JLabel jLabel = new JLabel("请输入取款金额", JLabel.CENTER);
        jLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        jPanels[0].add(jLabel, BorderLayout.CENTER);

        // 取款金额输入框
        input = new JTextField();
        input.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        input.setBounds(160,28,100,50);
        input.setTransferHandler(null);
        jPanels[1].setLayout(null);
        jPanels[1].add(input);

        // 提示信息
        JLabel information = new JLabel("请注意，你的账户里面还有" + user.getBalance() + "元余", JLabel.CENTER);
        information.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jPanels[2].add(information, BorderLayout.CENTER);

        // 取款按钮
        JButton jButton = new JButton("取款");
        jButton.setBounds(135,10,150,50);
        jButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        jButton.addActionListener(this);
        jPanels[3].setLayout(null);
        jPanels[3].add(jButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("取款".equals(e.getActionCommand())) {
            // 首先校验有效输入
                boolean flag = true;
                if (!Pattern.matches("^[0-9]+$", input.getText())) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "请输入有效金额！", "通知", JOptionPane.ERROR_MESSAGE);
                }
                // 输入有效
                if (flag) {
                    BigDecimal take = new BigDecimal(input.getText());
                    BigDecimal remain = new BigDecimal(String.valueOf(user.getBalance()));
                    // 判断是否是100的整数倍
                    if (!("0".equals(take.remainder(new BigDecimal("100")).toString()))) {
                        flag = false;
                        JOptionPane.showMessageDialog(this, "取款必须是100的整数倍！", "通知", JOptionPane.ERROR_MESSAGE);
                    }
                // 一次只能取20000元
                if (!Pattern.matches("^([12]\\d{4}|\\d{0,4})$", input.getText())) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "一次最多只能取20000RMB！", "通知", JOptionPane.ERROR_MESSAGE);
                }
                if (flag) {
                    // 判断取款金额是否超出余额
                    if (take.compareTo(remain) > 0) {
                        // 超出余额，做提示信息
                        JOptionPane.showMessageDialog(this, "余额不足!", "通知", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // 扣除金额，并提示取款成功,并返回个人信息页面
                        // 1.扣除金额
                        Service.changRemain(user.getPerson_id(), input.getText(), false);
                        // 2.提示成功信息
                        JOptionPane.showMessageDialog(this, "取款成功！", "通知", JOptionPane.INFORMATION_MESSAGE);
                        // 3.返回个人信息页面
                        mainView.showSpecifiedPanel(new InfoPanel());
                    }
                }
            }
        }
    }
}
