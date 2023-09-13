package com.zjr;

import com.zjr.HammingUtils;
import com.zjr.SimHashUtils;
import com.zjr.TxtIOUtils;
import org.junit.Test;

public class MainTest {

    @Test
    public void origAndAllTest(){
        String[] str = new String[6];//用于存储从不同文件中读取的文本内容
        str[0] = TxtIOUtils.readTxt("D:/test/orig.txt");
        //从指定路径读取文本文件的内容 存到str[0]
        str[1] = TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        str[2] = TxtIOUtils.readTxt("D:/test/orig_0.8_del.txt");
        str[3] = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_1.txt");
        str[4] = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_10.txt");
        str[5] = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_15.txt");
        String ansFileName = "D:/test/ansAll.txt";//存储结果文件的路径和名称
        for(int i = 0; i <= 5; i++){//逐一比较相似性 写入结果文件中
            double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str[0]), SimHashUtils.getSimHash(str[i]));
            TxtIOUtils.writeTxt(ans, ansFileName);
        }
    }

    @Test
    public void origAndOrigTest(){//将各个文本分别与原文本对比
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String ansFileName = "D:/test/ansOrigAndOrigTest.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans, ansFileName);
    }

    @Test
    public void origAndAddTest(){
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        String ansFileName = "D:/test/ansOrigAndAddTest.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans, ansFileName);
    }

    @Test
    public void origAndDelTest(){
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_del.txt");
        String ansFileName = "D:/test/ansOrigAndDelTest.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans, ansFileName);
    }

    @Test
    public void origAndDis1Test(){
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_1.txt");
        String ansFileName = "D:/test/ansOrigAndDis1Test.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans, ansFileName);
    }

    @Test
    public void origAndDis10Test(){
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_10.txt");
        String ansFileName = "D:/test/ansOrigAndDis10Test.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans, ansFileName);
    }

    @Test
    public void origAndDis15Test(){
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_dis_15.txt");
        String ansFileName = "D:/test/ansOrigAndDis15Test.txt";
        double ans = HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        TxtIOUtils.writeTxt(ans,ansFileName);
    }

}