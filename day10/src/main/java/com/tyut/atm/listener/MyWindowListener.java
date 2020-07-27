package com.tyut.atm.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static com.tyut.atm.service.Service.clearInfo;

/**
 * @author by 李大娃子
 * @classname MyWindowListener
 * @description TODO
 * @date 2020/7/25 11:18
 */
public class MyWindowListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // 在窗体被关闭后，清空info.txt中的数据
        clearInfo();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
