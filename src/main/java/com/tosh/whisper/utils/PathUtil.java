package com.tosh.whisper.utils;

import java.io.File;

import org.springframework.util.ClassUtils;

public class PathUtil
{
    private static String rootPath = null;
    
    public static String getPropertiesPath()
    {
        String propPath = File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "properties";
        return rootPath + propPath;
    }
    
    public static String getRootPath()
    {
        if (rootPath == null)
        {
            rootPath = ClassUtils.getDefaultClassLoader().getResource("")
                    .getPath();
        }
        return rootPath;
    }
    
}
