package org.yframework.toolkit;

import java.lang.reflect.Field;

/**
 * Description: Miscellany.<br>
 * Date: 2017/7/16 00:46<br>
 * Author: ysj
 */
public enum Miscellany
{
    INSTANCE;

    public <T> T mergeObject(T source, T target, Class<T> cls)
    {
        Field[] sourceFields = cls.getDeclaredFields();
        Field[] targetFields = cls.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++)
        {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try
            {
                if (!(sourceField.get(source) == null) && !"serialVersionUID".equals(sourceField.getName().toString()))
                {
                    targetField.set(target, sourceField.get(source));
                }
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return target;
    }

    /**
     * 判断一个字符串是否都为数字
     *
     * @param strNum
     * @return
     */
    public boolean isDigit(String strNum)
    {
        return strNum.matches("[0-9]{1,}");
    }

    /**
     * 把字符串转换成整型数
     *
     * @param str
     * @return
     */
    public int atoi(String str)
    {
        //这里要小心，需要判断有效性
        if (str == null || str.length() == 0)
        {
            return 0;
        }
        int nlen = str.length();
        double sum = 0;
        int sign = 1;
        int j = 0;
        //剔除空格
        while (str.charAt(j) == ' ') j++;
        //判断正数和负数
        if (str.charAt(j) == '+')
        {
            sign = 1;
            j++;
        }
        else if (str.charAt(j) == '-')
        {
            sign = -1;
            j++;
        }
        for (int i = j; i < nlen; i++)
        {
            char current = str.charAt(i);
            if (current >= '0' && current <= '9')
            {
                sum = sum * 10 + (current - '0');
            }
            else
            {
                //碰到非数字，退出转换
                break;
            }
        }
        sum = sum * sign;
        //这里要小心，需要判断范围
        if (sum > Integer.MAX_VALUE)
        {
            sum = Integer.MAX_VALUE;
        }
        else if (sum < Integer.MIN_VALUE)
        {
            sum = Integer.MIN_VALUE;
        }
        return (int) sum;
    }
}
