package practice;

import javax.swing.*;

/**
 * @author TurnHug
 * @date 2022/9/17 18:10
 */
public class Main {
    public static void main(String[] args) {
        // 设置程序显示的界面风格，可以去除
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Frame f = new Frame();
    }
}
