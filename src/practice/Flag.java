package practice;

import javax.swing.*;

/**
 * @author TurnHug
 * @date 2022/9/17 14:45
 */
public class Flag {

    // 操作数1，为了程序的安全，初值一定设置，这里我们设置为0。
    String str1 = "0";
    // 操作数2
    String str2 = "0";
    // 运算符
    String signal = "+";
    // 运算结果
    String result = "";

    // 以下k1至k2为状态开关

    // 开关1用于选择输入方向，将要写入str1或str2
    int k1 = 1;
    // 开关2用于记录符号键的次数，如果 k2>1 说明进行的是 2+3-9+8 这样的多符号运算
    int k2 = 1;
    // 开关3用于标识 str1 是否可以被清0 ，等于1时可以，不等于1时不能被清0
    int k3 = 1;
    // 开关4用于标识 str2 是否可以被清0
    int k4 = 1;
    // 开关5用于控制小数点可否被录入，等于1时可以，不为1时，输入的小数点被丢掉
    int k5 = 1;

    JButton store;

}
