package com.tyut.atm;

import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;
import com.tyut.atm.util.Util;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author by 李大娃子
 * @classname RegView
 * @description 注册页面
 * @date 2020/7/23 15:02
 */
public class RegView extends JFrame implements ActionListener {

    private JButton regBtn;

    private JTextField jtUsername;

    private JTextField jtPassword;

    private JTextField jtAge;

    private JTextField jtAddress;

    private JTextField jtPersonId;

    private JTextField jtPhone;

    private JTextField jtBirth;

    private JRadioButton jRadioButton;

    private JRadioButton jRadioButton1;

    private AutoTellerMachine autoTellerMachine;
    /**
     * 初始化界面
     */
    public RegView(AutoTellerMachine autoTellerMachine) {
        this.autoTellerMachine = autoTellerMachine;

        this.setTitle("注册");
        this.setSize(500,640);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(8, 1));
        this.setResizable(false);

        // 构造8个面板
        JPanel[] jPanel = new JPanel[8];
        for (int i = 0; i < jPanel.length; i++) {
            jPanel[i] = new JPanel(null);
            this.add(jPanel[i]);
        }

        // 姓名列
        JLabel jlUsername = new JLabel("姓名:");
        jlUsername.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlUsername.setBounds(60,15,80,50);
        jtUsername = new JTextField();
        jtUsername.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtUsername.setBounds(150,23,300,35);
        JLabel usernameLimit = new JLabel("请输入您的真实姓名");
        usernameLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        usernameLimit.setBounds(152,50,300,35);
        usernameLimit.setForeground(Color.BLUE);
        jtUsername.setTransferHandler(null);

        jPanel[0].add(jlUsername);
        jPanel[0].add(jtUsername);
        jPanel[0].add(usernameLimit);


        // 密码列
        JLabel jlPassword = new JLabel("密码:");
        jlPassword.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlPassword.setBounds(60,15,80,50);
        jtPassword = new JPasswordField();
        jtPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtPassword.setBounds(150,23,300,35);
        JLabel passwordLimit = new JLabel("字母开头，长度在6~18之间，只能包含字母数字下划线");
        passwordLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        passwordLimit.setBounds(152,50,300,35);
        passwordLimit.setForeground(Color.BLUE);
        jtPassword.setTransferHandler(null);
        jPanel[1].add(jlPassword);
        jPanel[1].add(jtPassword);
        jPanel[1].add(passwordLimit);

        // 性别列
        JLabel jlSex = new JLabel("性别:");
        jlSex.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlSex.setBounds(60,15,80,50);
        // 性别单选按钮
        ButtonGroup option = new ButtonGroup();
        jRadioButton = new JRadioButton("男");
        jRadioButton.setSelected(true);
        jRadioButton.addActionListener(this);
        jRadioButton1 = new JRadioButton("女");
        jRadioButton1.addActionListener(this);
        jRadioButton.setBounds(150,15,50,50);
        jRadioButton1.setBounds(200,15,50,50);
        option.add(jRadioButton);
        option.add(jRadioButton1);
        jPanel[2].add(jlSex);
        jPanel[2].add(jRadioButton);
        jPanel[2].add(jRadioButton1);

        // 年龄
        JLabel jlAge = new JLabel("年龄:");
        jlAge.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlAge.setBounds(250,15,80,50);
        jtAge = new JTextField();
        jtAge.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtAge.setBounds(300,23,150,35);
        JLabel ageLimit = new JLabel("注册年龄必须在18以上");
        ageLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        ageLimit.setBounds(302,50,300,35);
        ageLimit.setForeground(Color.BLUE);
        jtAge.setTransferHandler(null);
        jPanel[2].add(jlAge);
        jPanel[2].add(jtAge);
        jPanel[2].add(ageLimit);

        // 住址列
        JLabel jlAddress = new JLabel("住址:");
        jlAddress.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlAddress.setBounds(60,15,80,50);
        jtAddress = new JTextField();
        jtAddress.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtAddress.setBounds(150,23,300,35);
        JLabel addressLimit = new JLabel("地址必须为有效地址");
        addressLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        addressLimit.setBounds(152,50,300,35);
        addressLimit.setForeground(Color.BLUE);
        jtAddress.setTransferHandler(null);
        jPanel[3].add(jlAddress);
        jPanel[3].add(jtAddress);
        jPanel[3].add(addressLimit);

        // 身份证号列
        JLabel jlPersonId = new JLabel("身份证号:");
        jlPersonId.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlPersonId.setBounds(60,15,80,50);
        jtPersonId = new JTextField();
        jtPersonId.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        jtPersonId.setBounds(150,23,300,35);
        JLabel idLimit = new JLabel("请输入你的真实身份证号码");
        idLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        idLimit.setBounds(152,50,300,35);
        idLimit.setForeground(Color.BLUE);
        jtPersonId.setTransferHandler(null);
        jPanel[4].add(jlPersonId);
        jPanel[4].add(jtPersonId);
        jPanel[4].add(idLimit);

        // 电话列
        JLabel jlPhone = new JLabel("电话:");
        jlPhone.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlPhone.setBounds(60,15,80,50);
        jtPhone = new JTextField();
        jtPhone.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtPhone.setBounds(150,23,300,35);
        JLabel phoneLimit = new JLabel("联系方式必须有效");
        phoneLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        phoneLimit.setBounds(152,50,300,35);
        phoneLimit.setForeground(Color.BLUE);
        jtPhone.setTransferHandler(null);
        jPanel[5].add(jlPhone);
        jPanel[5].add(jtPhone);
        jPanel[5].add(phoneLimit);

        // 出生日期列
        JLabel jlBirth = new JLabel("出生日期:");
        jlBirth.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jlBirth.setBounds(60,15,80,50);
        jtBirth = new JTextField();
        jtBirth.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jtBirth.setBounds(150,23,300,35);
        JLabel birthLimit = new JLabel("格式为'yyyy-MM-dd'");
        birthLimit.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        birthLimit.setBounds(152,50,300,35);
        birthLimit.setForeground(Color.BLUE);
        jtBirth.setTransferHandler(null);
        jPanel[6].add(jlBirth);
        jPanel[6].add(jtBirth);
        jPanel[6].add(birthLimit);

        // 注册按钮列
        regBtn = new JButton("注册");
        regBtn.addActionListener(this);
        regBtn.setBounds(190,15,100,35);
        regBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        jPanel[7].add(regBtn);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regBtn) {
            boolean flag = true;
            StringBuilder information = new StringBuilder();
            information.append("<html>");

            // 输入有效性校验
            if (!Pattern.matches("^[\\u4E00-\\u9FA5]{2,4}$", jtUsername.getText())) {
                flag = false;
                information.append("请输入有效的用户名!<br/>");
            }
            if (!Pattern.matches("^[a-zA-Z]\\w{5,17}$", jtPassword.getText())) {
                flag = false;
                information.append("密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线！<br/>");
            }
            if (!Pattern.matches("^(1[89]|[2-8][0-9]|90)$", jtAge.getText())) {
                flag = false;
                information.append("年龄不符合标准！<br/>");
            }
            if (StringUtils.isBlank(jtAddress.getText())) {
                flag = false;
                information.append("请填写住址！<br/>");
            }
            if (!Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$", jtPersonId.getText())) {
                flag = false;
                information.append("请填写正确的身份证号！<br/>");
            }
            if (!Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", jtPhone.getText())) {
                flag = false;
                information.append("请填写正确的中国大陆地区手机号！<br/>");
            }
            if (!Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", jtBirth.getText())) {
                flag = false;
                information.append("请填写有效出生日期！<br/>");
            }
            if (!Service.uniqueUser(jtPersonId.getText())) {
                JOptionPane.showMessageDialog(this, "用户已被注册！", "通知", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!flag) {
                    information.append("</html>");
                    JOptionPane.showMessageDialog(this, information.toString(), "通知", JOptionPane.ERROR_MESSAGE);
                } else {
                    User user = new User();
                    user.setName(jtUsername.getText());
                    user.setPassword(jtPassword.getText());
                    user.setAge(jtAge.getText());
                    user.setSex(jRadioButton.isSelected() ? jRadioButton.getText() : jRadioButton1.getText());
                    user.setAddress(jtAddress.getText());
                    user.setPerson_id(jtPersonId.getText());
                    user.setPhone(jtPhone.getText());
                    user.setBorn(jtBirth.getText());
                    // 随机生成银行卡号
                    user.setCard_id(Util.getUUID());
                    // 设置初始余额
                    user.setBalance(new BigDecimal("0"));

                    // 添加到文件中
                    Service.addUser(user, Service.USER_FILE_PATH);
                    // 注册成功提示成功信息
                    JOptionPane.showMessageDialog(this, "注册成功！", "通知", JOptionPane.INFORMATION_MESSAGE);
                    // 销毁当前页面
                    this.dispose();
                    autoTellerMachine.dispose();
                    new AutoTellerMachine();
                }
            }
        }
    }
}
