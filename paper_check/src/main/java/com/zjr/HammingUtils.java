package com.zjr;

public class HammingUtils {

    /**
     * 输入两个simHash值，计算它们的海明距离
     * @param simHash1
     * @param simHash2
     * @return 海明距离
     */
    public static int getHammingDistance(String simHash1, String simHash2) {//传入两个哈希 返回海明距离
        int distance = 0;
        if (simHash1.length() != simHash2.length()) {
            // 海明距离是通过比较每个位来计算的。如果两个simHash值的长度不相等，则返回-1表示出错。
            distance = -1;
        } else {
            for (int i = 0; i < simHash1.length(); i++) {
                // 每一位进行比较
                //在每一次循环中，代码比较 simHash1 和 simHash2 在相同位置（位索引 i）的字符是否相等。
                //如果这两个字符不相等（即对应位上的比特值不同），则将 distance 变量递增1，表示海明距离增加了1。
                if (simHash1.charAt(i) != simHash2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * 输入两个simHash值，输出相似度
     * @param simHash1
     * @param simHash2
     * @return 相似度
     */
    public static double getSimilarity(String simHash1, String simHash2) {
        // 通过 simHash1 和 simHash2 获得它们的海明距离
        int distance = getHammingDistance(simHash1, simHash2);
        // 通过海明距离计算出相似度，并返回
        return 0.01 * (100 - distance * 100 / 128);
        //计算了海明距离相对于最大可能距离的百分比。128 是simHash的长度，因此是最大可能的海明距离。
        // 将百分比从100中减去，以得到相似度的百分比
    }

}