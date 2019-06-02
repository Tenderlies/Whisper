package com.tosh.whisper.utils;

import java.util.UUID;

public class IDUtil
{
    public static String generateUUIDString()
    {
        return generateUUID(null).toString();
    }
    
    public static String generateUUIDString(String uniqueString)
    {
        return generateUUID(uniqueString).toString();
    }
    
    public static long generateId(String uniqueString)
    {
        return Math.abs(generateUUID(uniqueString).getMostSignificantBits());
    }
    
    private static UUID generateUUID(String str)
    {
        return StringUtil.isEmpty(str) ? UUID.randomUUID() : UUID
                .nameUUIDFromBytes(str.getBytes());
    }
}
