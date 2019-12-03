package org.yframework.toolkit;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtil extends StringUtils
{
    private static final char _SEPARATOR = '_';
    private static final String _ENCODING = "UTF-8";
    private static StringUtil instance;

    static
    {
        instance = new StringUtil();
    }

    private StringUtil()
    {
    }

    public static StringUtil getInstance()
    {
        return instance;
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public byte[] getBytes(String str)
    {
        if (str != null)
        {
            try
            {
                return str.getBytes(_ENCODING);
            }
            catch (UnsupportedEncodingException e)
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public String toString(byte[] bytes)
    {
        try
        {
            return new String(bytes, _ENCODING);
        }
        catch (UnsupportedEncodingException e)
        {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public boolean inString(String str, String... strs)
    {
        if (str != null)
        {
            for (String s : strs)
            {
                if (str.equals(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public String replaceHtml(String html)
    {
        if (isBlank(html))
        {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html
     * @return
     */
    public String replaceMobileHtml(String html)
    {
        if (html == null)
        {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public String abbr(String str, int length)
    {
        if (str == null)
        {
            return "";
        }
        try
        {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray())
            {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3)
                {
                    sb.append(c);
                }
                else
                {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public String abbr2(String param, int length)
    {
        if (param == null)
        {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int n = 0;
        char temp;
        boolean isCode = false; // 是不是HTML代码
        boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
        for (int i = 0; i < param.length(); i++)
        {
            temp = param.charAt(i);
            if (temp == '<')
            {
                isCode = true;
            }
            else if (temp == '&')
            {
                isHTML = true;
            }
            else if (temp == '>' && isCode)
            {
                n = n - 1;
                isCode = false;
            }
            else if (temp == ';' && isHTML)
            {
                isHTML = false;
            }
            try
            {
                if (!isCode && !isHTML)
                {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            if (n <= length - 3)
            {
                result.append(temp);
            }
            else
            {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
        // 去掉不需要结素标记的HTML标记
        temp_result = temp_result.replaceAll("</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>", "");
        // 去掉成对的HTML标记
        temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
        // 用正则表达式取出标记
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
        Matcher m = p.matcher(temp_result);
        List<String> endHTML = new ArrayList<>();
        while (m.find())
        {
            endHTML.add(m.group(1));
        }
        // 补全不成对的HTML标记
        for (int i = endHTML.size() - 1; i >= 0; i--)
        {
            result.append("</");
            result.append(endHTML.get(i));
            result.append(">");
        }
        return result.toString();
    }

    /**
     * 转换为Double类型
     */
    public Double toDouble(Object val)
    {
        if (val == null)
        {
            return 0D;
        }
        try
        {
            return Double.valueOf(trim(val.toString()));
        }
        catch (Exception e)
        {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public Float toFloat(Object val)
    {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public Long toLong(Object val)
    {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public Integer toInteger(Object val)
    {
        return toLong(val).intValue();
    }

    /**
     * 获得用户远程地址
     */
    public String getRemoteAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
        {
            try
            {
                ip = InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException ignored)
            {
                ip = "0.0.0.0";
            }
        }
        if (ip == null || ip.length() == 0 || "X-Real-IP".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }
        return ip;
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == _SEPARATOR)
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public String toCapitalizeCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public String toUnderScoreCase(String s)
    {
        if (s == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1))
            {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c))
            {
                if (!upperCase || !nextUpperCase)
                {
                    sb.append(_SEPARATOR);
                }
                upperCase = true;
            }
            else
            {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 如果不为空，则设置值
     *
     * @param target
     * @param source
     */
    public void setValueIfNotBlank(String target, String source)
    {
        if (isNotBlank(source))
        {
            target = source;
        }
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串
     *                     例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public String jsGetVal(String objectString)
    {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++)
        {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    /**
     * 判定字符是否中文
     * eg: StringUtil.isZh('你');
     * ==>true
     *
     * @param ch
     * @returns {boolean}
     */
    public boolean isZh(char ch)
    {
        return (ch >= '\u4e00' && ch <= '\u9fa5') || (ch >= '\uf900' && ch <= '\ufa2d');
    }

    /**
     * 指定字符编码获取字符串
     * eg: StringUtil.lengthZh("hello 你", CharsetEnum.UTF_8.getCharset());
     * ==>8
     *
     * @param str     原字符串
     * @param charset 字符编码
     * @returns {Number}
     */
    public int length(String str, Charset charset)
    {
        if (StringUtil.isEmpty(str))
        {
            return 0;
        }
        if (charset == null)
        {
            return str.length();
        }
        else
        {
            return str.getBytes(charset).length;
        }
    }

    /**
     * 截取当前字符串到指定长度
     * eg: StringUtil.cut("我是中国人,我爱我的祖国", CharsetEnum.UTF_8.getCharset(), 0, 5, false)
     * ==>我
     *
     * @param str      源字符串
     * @param charset  字符编码
     * @param startPos 起始位置
     * @param len      目标长度
     * @param isLeft   左侧截取
     * @return
     */
    public String substring(String str, Charset charset, int startPos, int len, boolean isLeft)
    {
        int lenTmp = 0;
        String result = null;
        if (StringUtil.isEmpty(str))
        {
            result = str;
        }
        else if (charset == null)
        {
            lenTmp = str.length();
            if (startPos >= lenTmp)
            {
                result = "";
            }
            else if (isLeft)
            {
                result = str.substring(startPos, len < 0 ? lenTmp : Math.min(lenTmp, startPos + len));
            }
            else
            {
                result = str.substring(len < 0 ? 0 : Math.max(lenTmp - startPos - len, 0), lenTmp - startPos);
            }
        }
        else
        {
            byte[] strBs = str.getBytes(charset);
            lenTmp = strBs.length;
            if (startPos >= lenTmp)
            {
                result = "";
            }
            else
            {
                len = len < 0 ? lenTmp - startPos : Math.min(len, lenTmp - startPos);
                byte[] targetBs = new byte[len];
                if (isLeft)
                {
                    System.arraycopy(strBs, startPos, targetBs, 0, len);
                    result = new String(targetBs, charset);
                }
                else
                {
                    System.arraycopy(strBs, lenTmp - startPos - len, targetBs, 0, len);
                    result = new String(targetBs, charset);
                }
            }

        }
        return result;
    }

    /**
     * 截取当前字符串到指定长度
     * eg: StringUtil.cut("我是中国人,我爱我的祖国", CharsetEnum.UTF_8.getCharset(), 5, false)
     * ==>我
     *
     * @param str     源字符串
     * @param charset 字符编码
     * @param len     目标长度
     * @param isLeft  左侧截取
     * @return
     */
    public String substring(String str, Charset charset, int len, boolean isLeft)
    {
        return this.substring(str, charset, 0, len, isLeft);
    }

    /**
     * 截取当前字符串到指定长度
     * eg: StringUtil.cut("我是中国人,我爱我的祖国", CharsetEnum.UTF_8.getCharset(), 0, 5)
     * ==>我
     *
     * @param str      源字符串
     * @param charset  字符编码
     * @param startPos 起始位置
     * @param len      目标长度
     * @return
     */
    public String substring(String str, Charset charset, int startPos, int len)
    {
        return this.substring(str, charset, startPos, len, true);
    }

    /**
     * 补齐或截断字符串。
     * eg: StringUtil.padding("456", CharsetEnum.UTF_8.getCharset(), null, 5, "0", true, true)
     * ==>"00456"
     *
     * @param str     原始字符串
     * @param charset 字符编码
     * @param len     目标长度
     * @param fillStr 待补的字符串
     * @param isLeft  左边补齐/截断
     * @param isCut   当长度超过指定长度时是否截断
     * @return
     */
    public String padding(String str, Charset charset, int len, String fillStr, boolean isLeft, boolean isCut)
    {
        String result = str;
        if (str == null)
        {
            result = "";
        }
        int lenFill = this.length(fillStr, charset);
        int lenSrc = this.length(result, charset);
        if (lenSrc < len && lenFill != 0)
        {
            StringBuffer loc_sb = new StringBuffer();
            for (int i = 0, lenI = (len - lenSrc) / lenFill; i < lenI; i++)
            {
                loc_sb.append(fillStr);
            }
            if (isLeft)
            {
                result = loc_sb + result;
            }
            else
            {
                result = result + loc_sb;
            }
        }
        else if (isCut && lenSrc > len)
        {
            result = this.substring(result, charset, len, isLeft);
        }
        else
        {
            result = str;
        }
        return result;
    }

    /**
     * 补齐或截断字符串。
     * eg: StringUtil.padding("456", 5, "0", true, true)
     * ==>"00456"
     *
     * @param str     原始字符串
     * @param len     目标长度
     * @param fillStr 待补的字符串
     * @param isLeft  左边补齐/截断
     * @param isCut   当长度超过指定长度时是否截断
     * @return
     */
    public String padding(String str, int len, String fillStr, boolean isLeft, boolean isCut)
    {
        return this.padding(str, null, len, fillStr, isLeft, isCut);
    }

    /**
     * 左补齐
     * eg: StringUtil.paddingLeft("456", 5, "#", true)
     * ==>"##456"
     *
     * @param str
     * @param len
     * @param fillStr
     * @param isCut   当长度超过指定长度时是否截断
     * @return {String}
     */
    public String paddingLeft(String str, int len, String fillStr, boolean isCut)
    {
        return this.padding(str, null, len, fillStr, true, isCut);
    }

    /**
     * 右补齐
     * eg: StringUtil.paddingRight("456789", 4, "0", true)
     * ==>"4567"
     *
     * @param str
     * @param len
     * @param fillStr
     * @param isCut   当长度超过指定长度时是否截断
     * @returns {String}
     */
    public String paddingRight(String str, int len, String fillStr, boolean isCut)
    {
        return this.padding(str, null, len, fillStr, false, isCut);
    }

    /**
     * 左补齐，如果源字符串长度大于目标长度，不截断。
     * eg: StringUtil.paddingLeft("456", 5, "#")
     * ==>"##456"
     *
     * @param str
     * @param len
     * @param fillStr
     * @return {String}
     */
    public String paddingLeft(String str, int len, String fillStr)
    {
        return this.padding(str, null, len, fillStr, true, false);
    }

    /**
     * 右补齐，如果源字符串长度大于目标长度，不截断。
     * eg: StringUtil.paddingRight("456789", 4, "0")
     * ==>"456789"
     *
     * @param str
     * @param len
     * @param fillStr
     * @returns {String}
     */
    public String paddingRight(String str, int len, String fillStr)
    {
        return this.padding(str, null, len, fillStr, false, false);
    }

    /**
     * 去除字符串中指定的字符串
     * eg: StringUtil.trim("45678945", "45", 'A')
     * ==>"6789"
     *
     * @param str      源字符串
     * @param trimStr  待去除的字符串
     * @param trimType 去除位置 'L'-前去除 'A'-前后去除 'R'-后去除
     * @return
     */
    public String trim(String str, String trimStr, char trimType)
    {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(trimStr))
        {
            return str;
        }
        int trimLen = trimStr.length();
        String strTmp = str;
        int index = 0;
        int len = strTmp.length() - trimLen;
        //LEFT
        if (trimType == 'L' || trimType == 'A')
        {
            while (index <= len && strTmp.substring(index, index + trimLen).equals(trimStr))
            {
                index += trimLen;
            }
            strTmp = strTmp.substring(index);
        }
        //RIGHT
        index = strTmp.length();
        if (trimType == 'A' || trimType == 'R')
        {
            while (index >= trimLen && strTmp.substring(index - trimLen, index).equals(trimStr))
            {
                index -= trimLen;
            }
            strTmp = strTmp.substring(0, index);
        }
        return strTmp;
    }

    /**
     * 去除字符串前后指定的字符串
     * eg: StringUtil.trim("45678945", "45")
     * ==>"6789"
     *
     * @param str     源字符串
     * @param trimStr 待去除的字符串
     * @return
     */
    public String trim(String str, String trimStr)
    {
        return this.trim(str, trimStr, 'A');
    }

    /**
     * 去除字符串前指定的字符串
     * eg: StringUtil.trimLeft("45678945", "45")
     * ==>"678945"
     *
     * @param str     源字符串
     * @param trimStr 待去除的字符串
     * @return
     */
    public String trimLeft(String str, String trimStr)
    {
        return this.trim(str, trimStr, 'L');
    }

    /**
     * 去除字符串后指定的字符串
     * eg: StringUtil.trimRight("45678945", "45")
     * ==>"456789"
     *
     * @param str     源字符串
     * @param trimStr 待去除的字符串
     * @return
     */
    public String trimRight(String str, String trimStr)
    {
        return this.trim(str, trimStr, 'R');
    }


    /**
     * 字符串转换成十六进制字符串
     *
     * @param str
     * @return
     */
    public String toHexStr(String str, String encoding) throws UnsupportedEncodingException
    {
        char[] chars = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes(encoding);
        int bit;
        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    public String fromHexStr(String hexStr, String encoding) throws UnsupportedEncodingException
    {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++)
        {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes, encoding);
    }

    /**
     * 判断是否为定长的数字
     *
     * @param str
     * @param len
     * @return
     */
    public boolean isNumber(String str, int len)
    {
        return str.matches("\\d{" + len + "}");
    }
}
