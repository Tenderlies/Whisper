/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2017年1月4日
 */
package com.tosh.whisper.utils;

/**
 * ClassName: StringUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年1月4日
 */
public class StringUtil
{
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str);
    }
    
    public static String htmlFilter(String str)
    {
        if (str == null)
        {
            return (null);
        }
        
        char content[] = new char[str.length()];
        str.getChars(0, str.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++)
        {
            switch (content[i])
            {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());
    }
}
