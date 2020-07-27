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
 * @classname depositBtn
 * @description TODO
 * @date 2020/7/23 18:44
 */
public class DepositPanel extends JPanel implements ActionListener {

    private User user;

    private JTextField input;

    private MainView mainView;

    public DepositPanel(MainView mainView) {
        this.mainView = mainView;
        user = loadUser();

        this.setLayout(new GridLayout(3,1));

        JPanel[] jPanels = new JPanel[3];
        for (int i = 0; i < jPanels.length; i++) {
            jPanels[i] = new JPanel(new BorderLayout());
            this.add(jPanels[i]);
        }

        // 请输入存款余额
        JLabel jLabel = new JLabel("请输入存款金额", JLabel.CENTER);
        jLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        jPanels[0].add(jLabel, BorderLayout.CENTER);

        // 存款金额输入框
        input = new JTextField();
        input.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        input.setBounds(160,28,100,50);
        input.setTransferHandler(null);
        jPanels[1].setLayout(null);
        jPanels[1].add(input);

        // 存款按钮
        JButton jButton = new JButton("存款");
        jButton.setBounds(135,10,150,50);
        jButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        jButton.addActionListener(this);
        jPanels[2].setLayout(null);
        jPanels[2].add(jButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("存款".equals(e.getActionCommand())) {
            // 首先校验有效输入
            boolean flag = true;
            if (!Pattern.matches("^[0-9]+$", input.getText())) {
                flag = false;
                JOptionPane.showMessageDialog(this, "请输入有效金额！", "通知", JOptionPane.ERROR_MESSAGE);
            }
            // 输入有效
            if (flag) {
                BigDecimal to = new BigDecimal(input.getText());
                // 判断是否是100的整数倍
                if (!(to.remainder(new BigDecimal("100")).toString().equals("0"))) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "存款金额必须是100的整数倍！", "通知", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (flag) {
                // 1.修改金额
                Service.changRemain(user.getPerson_id(), input.getText(), true);
                // 2.提示成功信息
                JOptionPane.showMessageDialog(this, "存款成功！", "通知", JOptionPane.INFORMATION_MESSAGE);
                // 3.跳转到个人页面
                mainView.showSpecifiedPanel(new InfoPanel());
            }
        }
    }
}
