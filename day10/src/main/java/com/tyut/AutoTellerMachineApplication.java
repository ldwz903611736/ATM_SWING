package com.tyut;

import com.tyut.atm.AutoTellerMachine;
import com.tyut.cmd.Index;

import java.util.Scanner;

/**
 * @author by 李大娃子
 * @classname AutoTellerMachineApplication
 * @description 启动类
 * @date 2020/7/27 11:54
 */
public class AutoTellerMachineApplication {
    public static void main(String[] args) {
        System.out.println("*******************************************************");
        System.out.println("*********    欢 迎 使 用 ATM 提 款 服 务 系 统    *********");
        System.out.println("*********\t\t请输入你想进行的服务方式\t\t\t  *********");
        System.out.println("*********\t\t1、图形化界面系统\t\t\t\t  *********");
        System.out.println("*********\t\t2、控制台命令模式\t\t\t\t  *********");
        System.out.println("*********\t\t注：请在英文状态下输入！\t\t\t  *********");
        System.out.println("*********\t\t否则会出现不可预知的错误！\t\t  *********");
        System.out.println("*******************************************************");
        System.out.println("\t请输入你要执行的功能编号");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        boolean sign = true;
        while (sign) {
            switch (text) {
                case "1":
                    // 设置UI
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Nimbus".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    new AutoTellerMachine();
                    sign = false;
                    break;
                case "2":
                    Index index = new Index();
                    index.go();
                    sign = false;
                    break;
                default:
                    System.err.println("请输入正确编号！");
                    text = scanner.next();
                    break;
            }
        }
    }
}
