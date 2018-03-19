package com.becypress.toolkit;

/**
 * Description: ByteUtil.<br>
 * Date: 2017/12/27 19:25<br>
 * Author: ysj
 */
public enum ByteUtil
{
    INSTANCE;

    /**
     * 整型转换为4位字节数组
     *
     * @param intValue
     * @return
     */
    public byte[] int2Byte(int intValue)
    {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++)
        {
            b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
        }
        return b;
    }

    /**
     * 4位字节数组转换为整型
     *
     * @param b
     * @return
     */
    public int byte2Int(byte[] b)
    {
        int intValue = 0;
        for (int i = 0; i < b.length; i++)
        {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }
}
