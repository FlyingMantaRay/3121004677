package com.zjr;

import com.zjr.HammingUtils;
import com.zjr.SimHashUtils;
import com.zjr.TxtIOUtils;
public class MainPaperCheck {
//命令 java MainPaperCheck D:/test/orig.txt D:/test/orig_0.8_add.txt D:/test/ansAll.txt
    public static void main(String[] args) {
        // 从命令行输入的路径名读取对应的文件，将文件的内容转化为对应的字符串
        String str0 = TxtIOUtils.readTxt(args[0]);//从命令行参数中获取一个文件路径
        //用TxtIOUtils.readTxt 方法来读取该文件的内容并将其存储在名为 str0 的字符串变量中
        String str1 = TxtIOUtils.readTxt(args[1]);
        //args[1] 表示第二个命令行参数 是一个文件路径，用于指定第二个要处理的文本文件
        String resultFileName = args[2];//获取第三个参数，也就是结果文件的名称，并将其存储在resultFileName中
        // 由字符串得出对应的 simHash值
        String simHash0 = SimHashUtils.getSimHash(str0);
        String simHash1 = SimHashUtils.getSimHash(str1);
        // 由 simHash值求出相似度
        double similarity = HammingUtils.getSimilarity(simHash0, simHash1);
        // 把相似度写入最后的结果文件中
        TxtIOUtils.writeTxt(similarity, resultFileName);
        // 退出程序
        System.exit(0);
    }

}