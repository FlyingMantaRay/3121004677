package com.zjr;

import java.io.*;

/**
 * 读写txt文件的工具类
 */
public class TxtIOUtils {

    /**
     * 读出txt文件
     * 传入文件绝对路径，将文件内容转化为 String字符串输出
     * @param txtPath 文件路径
     * @return 文件内容
     */
    public static String readTxt(String txtPath) {
        String str = "";//创建空字符串来储存文本内容
        String strLine;//初始化一个变量 储存每一行的文本
        // 将 txt文件按行读入 str中
        File file = new File(txtPath);//创建一个文件对象 txtpath是关联到这个对象中的路径
        FileInputStream fileInputStream = null;//这个类用于从文件中读取字节流
        try {
            fileInputStream = new FileInputStream(file);//填充上面的字节流空对象 并与file关联
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            //inputStreamReader这个对象可以将字节流转换成字符流 将从 fileInputStream 中读取的字节流转换成字符流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //inputStreamReader 对象作为参数传递给 BufferedReader 以指定要读取的字符流来源
            // BufferedReader可以从字符流中逐行读取文本
            // 字符串拼接
            while ((strLine = bufferedReader.readLine()) != null) {
                str += strLine;
            }
            // 关闭资源
            inputStreamReader.close();
            bufferedReader.close();
            fileInputStream.close();
        } catch (IOException e) {//处理读异常
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 写入txt文件
     * 传入内容、文件全路径名，将内容写入文件并换行
     * @param txtElem 传入的内容
     * @param txtPath 写入的文件路径
     */
    public static void writeTxt(double txtElem,String txtPath){
        String str = Double.toString(txtElem);//写入内容转str型
        File file = new File(txtPath);//同上 将路径名关联文件对象
        FileWriter fileWriter = null;//类似上面 FileWriter 是用于写入字符数据到文件的类
        try {
            fileWriter = new FileWriter(file, true);//创建一个 FileWriter 对象，将其与指定的文件 file 相关联
            //true 表示以追加模式打开文件
            fileWriter.write(str, 0, (str.length() > 3 ? 4 : str.length()));
            //0 表示从字符串的第一个字符开始写入 如果字符串的长度大于3，则写入4个字符，否则写入字符串的长度
            fileWriter.write("\r\n");//使用 fileWriter 对象将 "\r\n" 字符串写入文件
            // 关闭资源
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
