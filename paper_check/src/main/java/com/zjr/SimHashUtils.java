package com.zjr;
import com.hankcs.hanlp.HanLP;
import com.zjr.ShortStringException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

public class SimHashUtils {

    /**
     * 传入String，计算出它的hash值，并以字符串形式输出
     * @param str 传入的Srting类型字符串
     * @return 返回str的hash值
     */
    //SimHash是一种用于文本相似性和近似重复检测的技术。代码的主要步骤包括：
    //首先，检查输入字符串长度是否小于200，如果是，抛出 com.zjr.ShortStringException 异常。
    //创建一个长度为128的整数数组 v，用于表示特征向量。
    //使用外部依赖的HanLP库提取输入字符串的关键词列表。
    //对每个关键词执行以下操作：
    //获取关键词的哈希值，使用 getHash 方法。
    //如果哈希值的长度不足128位，则在低位以0补齐。
    //根据关键词的哈希值进行加权，然后合并到特征向量中。
    //最后，将特征向量降维，生成SimHash值，并以字符串形式返回。
    public static String getHash(String str){
        try{
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //创建一个 MessageDigest 对象，用于执行 MD5 哈希算法
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
            //str.getBytes("UTF-8"): 将字符串 str 转换为 UTF-8 字符编码的字节数组。这是因为哈希函数通常作用于字节数组
            //使用之前创建的messageDigest对象 计算输入字节数组的哈希值。这将返回一个字节数组，表示输入数据的哈希值
            //得到的哈希值字节数组转换为一个 BigInteger 对象。这里的 1 表示要将此字节数组解释为一个正数
            //最后，将 BigInteger 对象转换为二进制字符串表示。.toString(2) 中的 2 参数指定要将该数字表示为二进制字符串
        }catch(Exception e){
            e.printStackTrace();//
            return str;//如果发生异常，代码将返回原始的输入字符串 str
        }
    }

    /**
     * 传入String,计算出它的simHash值，并以字符串形式输出
     * @param str 传入的Srting类型字符串
     * @return 返回str的simHash值
     */
    public static String getSimHash(String str){
        // 文本长度太短时HanLp无法取得关键字
        try{
            if(str.length() < 200) throw new ShortStringException("文本过短！");
            //首先检查输入字符串 str 的长度是否小于200个字符。如果是，它会抛出一个自定义的 com.zjr.ShortStringException 异常
        }catch (ShortStringException e){
            e.printStackTrace();
            return null;
        }
        // 用数组表示特征向量,取128位,从 0 1 2 位开始表示从高位到低位
        int[] v = new int[128];
        // 分词（使用了外部依赖hankcs包提供的接口）
        List<String> keywordList = HanLP.extractKeyword(str, str.length());//取出所有关键词
        //HanLP.extractKeyword(str, str.length()): 这是 HanLP 提供的一个函数，用于从输入的字符串 str 中提取关键词。
        // str.length() 用于指定要提取关键词的文本长度。
        //提取的关键词将会存储在 keywordList 变量中，这是一个泛型列表（List），其中每个元素都是一个字符串，表示一个提取出的关键词
        // hash
        int size = keywordList.size();//有多少个词
        int i = 0;//以i做外层循环
        for(String keyword : keywordList){
            // 获取hash值
            String keywordHash = getHash(keyword);
            //getHash 方法，将当前关键词 keyword 作为参数传递给该方法，并获取关键词的哈希值。
            // getHash 方法使用 MD5 哈希算法计算关键词的哈希值
            if (keywordHash.length() < 128) {
                // hash值可能少于128位，在低位以0补齐 最终达到128位
                int dif = 128 - keywordHash.length();//计算需要补齐的0的数量
                for (int j = 0; j < dif; j++) {//在哈希值的末尾添加0，以达到128位的长度
                    keywordHash += "0";
                }
            }
            // 加权、合并
            for (int j = 0; j < v.length; j++) {//遍历特征向量 v 中的每一位，从高位到低位
                // 对keywordHash的每一位与'1'进行比较
                if (keywordHash.charAt(j) == '1') {//检查关键词哈希值 keywordHash 的第 j 位是否为 '1'
                    //权重分10级，由词频从高到低，取权重10~0
                    v[j] += (10 - (i / (size / 10)));
                    // 如果当前位为 '1'，则将特征向量 v 的第 j 位的值增加权重。
                    // 权重的计算是基于词频，词频越高的关键词获得更大的权重。
                    // 权重的范围是从10到0，根据关键词在关键词列表中的位置进行分配，位置越靠前，权重越高
                } else {
                    v[j] -= (10 - (i / (size / 10)));
                    //如果当前位不是 '1'，则将特征向量 v 的第 j 位的值减去权重
                }
            }
            i++;
        }
        // 降维 将经过加权和合并后的特征向量 v 转化为最终的 SimHash 值，并以字符串形式返回
        String simHash = "";// 储存返回的simHash值
        for (int j = 0; j < v.length; j++) {
            // 从高位遍历到低位
            if (v[j] <= 0) {
                simHash += "0";
            } else {
                simHash += "1";
            }
            //根据特征向量 v 的每一位的值，决定在 SimHash 值的相应位置添加 "0" 或 "1"。
            // 如果特征向量中的某位小于等于0，就添加 "0"，否则添加 "1"。
        }
        return simHash;
    }

}
