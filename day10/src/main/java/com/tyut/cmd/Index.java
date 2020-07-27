package com.tyut.cmd;

import com.tyut.atm.entity.User;
import com.tyut.atm.service.Service;
import com.tyut.cmd.func.ChangePwd;
import com.tyut.cmd.func.Login;
import com.tyut.cmd.func.Regist;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author by 李大娃子
 * @classname index
 * @description TODO
 * @date 2020/7/26 16:34
 */
public class Index {

    private User user;

    public static void main(String[] args) {
        Index index = new Index();
        index.go();
    }

    public void go() {
        Scanner scanner = new Scanner(System.in);
        boolean sign = true;
        while (sign) {
            mainInterface();
            String text = scanner.next();
            switch (text) {
                case "1":
                    Regist regist = new Regist();
                    regist.reg();
                    break;
                case "2":
                    Login login = new Login();
                    this.user = login.log();
                    if (this.user == null) {
                        System.err.println("用户名和错误!");
                    }
                    break;
                case "3":
                    if (this.user == null) {
                        System.err.println("请先登录！");
                    } else {
                        System.out.println("请输入取款金额");
                        String money = scanner.next();
                        if (!(Pattern.matches("^[0-9]*$", money))) {
                            System.err.println("请输入有效金额！");
                        } else if ((new BigDecimal(money).compareTo(new BigDecimal(this.user.getBalance().toString()))) >= 0) {
                            System.err.println("超出金额！");
                        } else if (!(new BigDecimal(money).remainder(new BigDecimal("100"))).toString().equals("0")) {
                            System.err.println("取款金额必须是100的倍数，请重新输入！");
                        } else {
                            Service.changRemain(user.getPerson_id(), money, false);
                        }
                    }
                    break;
                case "4":
                    if (this.user == null) {
                        System.err.println("请先登录！");
                    } else {
                        System.out.println("请输入存款金额");
                        String money = scanner.next();
                        if (!(Pattern.matches("^[0-9]*$", money))) {
                            System.err.println("请输入有效金额!");
                        } else if (!(new BigDecimal(money).remainder(new BigDecimal("100"))).toString().equals("0")) {
                            System.err.println("存款金额必须是100的倍数！");
                        } else {
                            Service.changRemain(user.getPerson_id(), money, true);
                        }
                    }
                    break;
                case "5":
                    if (this.user == null) {
                        System.err.println("请先登录！");
                    } else {
                        ChangePwd changePwd = new ChangePwd();
                        changePwd.change(this.user);
                    }
                    break;
                case "6":
                    if (this.user == null) {
                        System.err.println("请先登录！");
                    } else {
                        this.user = Service.loadUser();
                        System.out.println("您的余额:" + this.user.getBalance());
                    }
                    break;
                case "7":
                    if (this.user == null) {
                        System.err.println("请先登录！");
                    } else {
                        System.out.println(this.user);
                    }
                    break;
                case "8":
                    if (this.user == null) {
                        System.out.println("请先登录");
                    } else {
                        System.out.print("\t确定要退出吗(Y/N)");
                        String s = scanner.next();
                        if (s.equalsIgnoreCase("Y")) {
                            sign = false;
                            System.out.println("程序退出!");
                            Service.clearInfo();
                        }
                    }
                    break;
                case "9":
                    sign = false;
                    Service.clearInfo();
                    break;
                case "0":
                    System.out.println("********************************************");
                    System.out.println("********\t\t关 于 本 软 件\t\t********");
                    System.out.println("********\t\t\t\t\t\t\t********");
                    System.out.println("********\t欢迎使用ATM取款系统！\t\t********");
                    System.out.println("********\t本软件使用Swing和Java\t\t********");
                    System.out.println("********\t程序代码写成\t\t\t\t********");
                    System.out.println("********\t作者:李佳峰\t\t\t\t********");
                    System.out.println("********\t2017级软件开发2班四组\t\t********");
                    System.out.println("********\t\t\t\t\t\t\t********");
                    System.out.println("********************************************");
                    break;
                default:
                    System.err.println("\t请输入正确选项");
                    break;
            }
        }
    }
    /**
     * 主界面
     */
    public void mainInterface() {
        System.out.println("*******************************************************");
        System.out.println("*********    欢 迎 使 用 ATM 提 款 服 务 系 统    *********");
        System.out.println("*********\t\t1、注册\t\t\t2、登录\t\t  *********");
        System.out.println("*********\t\t3、取款\t\t\t4、存款\t\t  *********");
        System.out.println("*********\t\t5、修改密码\t\t6、显示余额\t  *********");
        System.out.println("*********\t\t7、显示账户安全信息\t8、注销\t\t  *********");
        System.out.println("*********\t\t9、安全退出\t\t0、关于帮助\t  *********");
        System.out.println("*******************************************************" + "\t\t" + "注：请在英文状态下输入！");
        System.out.println("\t请输入你要执行的功能编号");
    }
}
