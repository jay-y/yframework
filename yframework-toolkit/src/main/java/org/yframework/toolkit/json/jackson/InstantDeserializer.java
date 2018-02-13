package org.yframework.toolkit.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.yframework.toolkit.y;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InstantDeserializer extends JsonDeserializer<Instant>
{
    private static final Map<String, String> DATE_REG_FORMAT = new HashMap<>();

    static
    {
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");//2014-03-12 12
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
        DATE_REG_FORMAT.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
        DATE_REG_FORMAT.put("^\\d{4}$", "yyyy");//2014
        DATE_REG_FORMAT.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        DATE_REG_FORMAT.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        DATE_REG_FORMAT.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        DATE_REG_FORMAT.put("^\\d{8}$", "yyyyMMdd");//20140312
        DATE_REG_FORMAT.put("^\\d{6}$", "yyyyMM");//201403
        DATE_REG_FORMAT.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
        DATE_REG_FORMAT.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
        DATE_REG_FORMAT.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
        DATE_REG_FORMAT.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        DATE_REG_FORMAT.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)
    }

    @Override
    public Instant deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        String dateStr = jp.getText();
        Date date = null;
        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateReplace;
        for (String key : DATE_REG_FORMAT.keySet())
        {
            if (Pattern.compile(key).matcher(dateStr).matches())
            {
                if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$") || key.equals("^\\d{2}\\s*:\\s*\\d{2}$"))
                {//13:05:34 或 13:05 拼接当前日期
                    dateStr = curDate + "-" + dateStr;
                }
                else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$"))
                {//21.1 (日.月) 拼接当前年份
                    dateStr = curDate.substring(0, 4) + "-" + dateStr;
                }
                dateReplace = dateStr.replaceAll("\\D+", "-");
                date = y.util().time().getDate(dateReplace, DATE_REG_FORMAT.get(key));
                break;
            }
        }
        return null != date ? date.toInstant() : null;
    }
}
