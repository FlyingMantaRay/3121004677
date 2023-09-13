package com.zjr;

import org.junit.Test;

public class SimHashUtilsTest {

    @Test
    public void getHashTest(){//测试了SimHashUtils.getHash方法
        String[] strings = {"余华", "是", "一位", "真正", "的", "作家"};
        for (String string : strings) {
            String stringHash = SimHashUtils.getHash(string);
            System.out.println(stringHash.length());
            System.out.println(stringHash);
        }
    }

    @Test
    public void getSimHashTest(){//测试了SimHashUtils.getSimHash
        String str0 = TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        System.out.println(SimHashUtils.getSimHash(str0));
        System.out.println(SimHashUtils.getSimHash(str1));
    }

}