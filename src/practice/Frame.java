package practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Vector;

/**
 * @author TurnHug
 * @date 2022/9/17 15:07
 */

public class Frame {
    Flag f = new Flag();
    Vector<JButton> vt = new Vector<>(20, 10);

    JFrame frame = new JFrame("简易计算器");  //程序主窗体
    private String result;
    // 创建一个 JTextField 对象并初始化。 JTextField 是用于显示操作和计算结果的文本框。
    // 参数 20 表明可以显示 20 列的文本内容
    JTextField result_TextField = new JTextField(result, 15); //result是计算的结果

    JButton clearButton = new JButton("清除");

    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");

    JButton buttonJia = new JButton("+");
    JButton buttonJian = new JButton("-");
    JButton buttonCheng = new JButton("*");
    JButton buttonChu = new JButton("/");
    JButton buttonDeng = new JButton("=");
    JButton buttonDian = new JButton(".");

    JPanel panelShang = new JPanel();
    JPanel panelXia = new JPanel();

    public Frame(){
        // 为按钮设置等效键，即可以通过对应的键盘按键来代替点击它
        button0.setMnemonic(KeyEvent.VK_0);
        button1.setMnemonic(KeyEvent.VK_1);
        button2.setMnemonic(KeyEvent.VK_2);
        button3.setMnemonic(KeyEvent.VK_3);
        button4.setMnemonic(KeyEvent.VK_4);
        button5.setMnemonic(KeyEvent.VK_5);
        button6.setMnemonic(KeyEvent.VK_6);
        button7.setMnemonic(KeyEvent.VK_7);
        button8.setMnemonic(KeyEvent.VK_8);
        button9.setMnemonic(KeyEvent.VK_9);
        buttonJia.setMnemonic(KeyEvent.VK_ADD);
        buttonJian.setMnemonic(KeyEvent.VK_SUBTRACT);
        buttonCheng.setMnemonic(KeyEvent.VK_MULTIPLY);
        buttonChu.setMnemonic(KeyEvent.VK_DIVIDE);
        buttonDian.setMnemonic(KeyEvent.VK_PERIOD);
        buttonDeng.setMnemonic(KeyEvent.VK_EQUALS);

        // 设置文本框为右对齐，使输入和结果都靠右显示
        result_TextField.setHorizontalAlignment(JTextField.RIGHT);

        panelXia.setLayout(new GridLayout(4,4,5,5));
        panelXia.add(button7);
        panelXia.add(button8);
        panelXia.add(button9);
        panelXia.add(buttonDeng);
        panelXia.add(button4);
        panelXia.add(button5);
        panelXia.add(button6);
        panelXia.add(buttonJia);
        panelXia.add(button1);
        panelXia.add(button2);
        panelXia.add(button3);
        panelXia.add(buttonJian);
        panelXia.add(button0);
        panelXia.add(buttonDian);
        panelXia.add(buttonChu);
        panelXia.add(buttonCheng);
        panelXia.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelShang.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=3;
        gbc.gridheight=1;
        gbc.ipadx = 5;


        panelShang.add(result_TextField,gbc);
        GridBagConstraints gbb = new GridBagConstraints();

//        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelShang.add(clearButton,gbb);
        panelShang.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        // 设置主窗口出现在屏幕上的位置
        frame.setLocation(300, 200);
        // 设置窗体不能调大小
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panelShang,BorderLayout.NORTH);
        frame.getContentPane().add(panelXia,BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        class Listener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ss = ((JButton) e.getSource()).getText();
                // store的作用类似于寄存器，用于记录是否连续按下符号键
                JButton store = (JButton) e.getSource();
                vt.add(store);
                if (f.k1 == 1) {
                    if (f.k3 == 1) {
                        f.str1 = "";

                        // 还原开关k5状态
                        f.k5 = 1;
                    }
                    f.str1 = f.str1 + ss;

                    f.k3 = f.k3 + 1;

                    // 显示结果
                    result_TextField.setText(f.str1);
                }else if (f.k1 == 2) {
                    if (f.k4 == 1) {
                        f.str2 = "";

                        // 还原开关k5状态
                        f.k5 = 1;
                    }
                    f.str2 = f.str2 + ss;
                    f.k4 = f.k4 + 1;
                    result_TextField.setText(f.str2);
                }
            }
        }

        class ListenerSignal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ss2 = ((JButton) e.getSource()).getText();
                f.store = (JButton) e.getSource();

                vt.add(f.store);

                if (f.k2 == 1) {
                    // 开关 k1 为 1 时向数 1 写输入值，为2时向数2写输入值。
                    f.k1 = 2;
                    f.k5 = 1;
                    f.signal = ss2;
                } else {
                    int a = vt.size();
                    JButton c = (JButton) vt.get(a - 2);

                    if (!(c.getText().equals("+"))
                            && !(c.getText().equals("-"))
                            && !(c.getText().equals("*"))
                            && !(c.getText().equals("/")))

                    {
                        cal();
                        f.str1 = f.result;
                        // 开关 k1 为 1 时，向数 1 写值，为2时向数2写
                        f.k1 = 2;
                        f.k5 = 1;
                        f.k4 = 1;
                        f.signal = ss2;
                    }

                }
                f.k2 = f.k2 + 1;// 按符号键的次数
            }
        }
        class ListenerEqual implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                f.store = (JButton) e.getSource();
                vt.add(f.store);
                cal();

                // 还原各个开关的状态
                f.k1 = 1;
                f.k2 = 1;
                f.k3 = 1;
                f.k4 = 1;

                f.str1 = f.result;
            }
        }
        class ListenerPoint implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                f.store = (JButton) e.getSource();
                vt.add(f.store);
                if (f.k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (f.k1 == 1) {
                        if (f.k3 == 1) {
                            f.str1 = "";
                            // 还原开关k5状态
                            f.k5 = 1;
                        }
                        f.str1 = f.str1 + ss2;

                        f.k3 = f.k3 + 1;

                        // 显示结果
                        result_TextField.setText(f.str1);

                    } else if (f.k1 == 2) {
                        if (f.k4 == 1) {
                            f.str2 = "";
                            // 还原开关k5的状态
                            f.k5 = 1;
                        }
                        f.str2 = f.str2 + ss2;

                        f.k4 = f.k4 + 1;

                        result_TextField.setText(f.str2);
                    }
                }

                f.k5 = f.k5 + 1;
            }
        }
        class ListenerClear implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                f.store = (JButton) e.getSource();

                vt.add(f.store);
                f.k5 = 1;
                f.k2 = 1;
                f.k1 = 1;
                f.k3 = 1;
                f.k4 = 1;
                f.str1 = "0";
                f.str2 = "0";
                f.signal = "";
                f.result = "";
                result_TextField.setText(f.result);
                vt.clear();
            }
        }

        // 注册各个监听器，即绑定事件响应逻辑到各个UI组件上
        ListenerEqual jtD = new ListenerEqual();

        // 监听数字键
        Listener jtS = new Listener();
        // 监听符号键
        ListenerSignal jtF = new ListenerSignal();
        // 监听清除键
        ListenerClear jtQ = new ListenerClear();
        // 监听小数点键
        ListenerPoint jtX = new ListenerPoint();
        button1.addActionListener(jtS);
        buttonDeng.addActionListener(jtD);
        buttonDian.addActionListener(jtX);
        buttonChu.addActionListener(jtF);
        buttonCheng.addActionListener(jtF);
        buttonJian.addActionListener(jtF);
        buttonJia.addActionListener(jtF);
        button2.addActionListener(jtS);
        button3.addActionListener(jtS);
        button4.addActionListener(jtS);
        button5.addActionListener(jtS);
        button6.addActionListener(jtS);
        button7.addActionListener(jtS);
        button8.addActionListener(jtS);
        button9.addActionListener(jtS);
        button0.addActionListener(jtS);
        clearButton.addActionListener(jtQ);

        // 窗体关闭事件的响应程序
        frame.addWindowListener(new WindowAdapter() {
            public void windowClose() {
                System.exit(0);
            }
        });

    }

    // 计算逻辑
    public void cal() {
        // 操作数1
        double a2;
        // 操作数2
        double b2;
        // 运算符
        String c = f.signal;
        // 运算结果
        double result2 = 0;

        if (c.equals("")) {
            result_TextField.setText("Please input operator");

        } else {
            // 手动处理小数点的问题
            if (f.str1.equals("."))
                f.str1 = "0.0";
            if (f.str2.equals("."))
                f.str2 = "0.0";
            a2 = Double.parseDouble(f.str1);
            b2 = Double.parseDouble(f.str2);

            if (c.equals("+")) {
                result2 = a2 + b2;
            }
            if (c.equals("-")) {
                result2 = a2 - b2;
            }
            if (c.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }
            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }

            }

            result = ((new Double(result2)).toString());

            result_TextField.setText(result);
        }
    }

}
