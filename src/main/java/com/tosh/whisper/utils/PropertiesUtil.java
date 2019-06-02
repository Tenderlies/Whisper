/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2016年10月30日
 */
package com.tosh.whisper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ClassName: PropertiesUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2016年10月30日
 */
public class PropertiesUtil
{
    public static Properties getProperties(String propUrl) throws IOException
    {
        Properties prop = new Properties();
        File file = new File(propUrl);
        FileInputStream in = new FileInputStream(file);
        prop.load(in);
        in.close();
        return prop;
    }
    
    public static boolean storeProperties(Properties prop, String propUrl)
    {
        File file = new File(propUrl);
        FileOutputStream out;
        try
        {
            out = new FileOutputStream(file);
            prop.store(out, "");
            out.close();
        }
        catch (IOException e)
        {
            return false;
        }
        return true;
    }
}
