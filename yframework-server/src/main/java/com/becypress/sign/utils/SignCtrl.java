package com.becypress.sign.utils;

import com.becypress.sign.annotation.SignField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.CharsetEnum;
import org.yframework.toolkit.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Description: PayCtrl.<br>
 * Date: 2017/9/15 17:53<br>
 * Author: ysj
 */
public enum SignCtrl
{
    INSTANCE;

    private final Logger log = LoggerFactory.getLogger(SignCtrl.class);

    /**
     * 通用签名生成
     * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
     * 特别注意以下重要规则：
     * ◆ 参数名ASCII码从小到大排序（字典序）；
     * ◆ 如果参数的值为空不参与签名；
     * ◆ 参数名区分大小写；
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段。
     * 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。
     *
     * @param o   数据
     * @param key 密钥
     * @return 签名字符串
     */
    public String gen(Object o, String key, SignCoder coder)
    {
        ArrayList<String> list = new ArrayList<>();
        Set<Field> fieldSet = MappingCtrl.INSTANCE.getAllFields(o);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(SignField.class)).
                forEach(field ->
                {
                    field.setAccessible(true);
                    try
                    {
                        if (field.get(o) != null && field.get(o) != "")
                        {
                            SignField signField = field.getAnnotation(SignField.class);
                            String name = StringUtil.isNotBlank(signField.name()) ? signField.name() : field.getName();
                            list.add(name + "=" + field.get(o) + "&");
                        }
                    }
                    catch (IllegalAccessException e)
                    {
                        log.error("签名生成异常: {}", e.getMessage());
                    }
                });
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        log.debug("{} Sign : {}", coder.getClass().getSimpleName(), result);
        result = coder.encode(result, CharsetEnum.UTF_8.getKey()).toUpperCase();
        log.debug("Sign Result: {}", result);
        return result;
    }
}