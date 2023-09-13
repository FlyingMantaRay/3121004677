package com.zjr;

import org.junit.Test;

public class TxtIOUtilsTest {

    @Test
    public void readTxtTest() {//检查TxtIOUtils类是否能够成功读取
        // 路径存在，正常读取
        String str = TxtIOUtils.readTxt("D:/test/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void writeTxtTest() {//检查TxtIOUtils类是否能够成功将一个 double 数组（elem）写入

        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            TxtIOUtils.writeTxt(elem[i], "D:/test/ans.txt");
        }
    }

    @Test
    public void readTxtFailTest() {//测试了当指定路径 "D:/test/none.txt" 不存在时，
        // TxtIOUtils类是否能够正确处理读取失败的情况
        String str = TxtIOUtils.readTxt("D:/test/none.txt");
    }

    @Test
    public void writeTxtFailTest() {
        //测试了当指定错误的路径 "User:/test/ans.txt" 时，TxtIOUtils类是否能够正确处理写入失败的情况
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            TxtIOUtils.writeTxt(elem[i], "User:/test/ans.txt");
        }
    }

}