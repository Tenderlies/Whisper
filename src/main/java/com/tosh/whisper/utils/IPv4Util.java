package com.tosh.whisper.utils;

/**
 * IPv4的工具类 ClassName: IPv4Util<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年3月9日
 */
public class IPv4Util
{
    public static boolean checkSameSegment(String ip1, String ip2,
            short maskLength)
    {
        int maskBitValue = getBitValueByAddress(getSubnetMaskByMaskLength(maskLength));
        return (getBitValueByString(ip1) & maskBitValue) == (getBitValueByString(ip2) & maskBitValue);
    }
    
    public static int getBitValueByString(String ipString)
    {
        return getBitValueByAddress(getAddressByString(ipString));
    }
    
    public static byte[] getNetAddress(byte[] address, byte[] subnetMask)
    {
        return getAddressByBitValue(getBitValueByAddress(address)
                & getBitValueByAddress(subnetMask));
    }
    
    public static byte[] getBroadcastAddress(byte[] address, byte[] subnetMask)
    {
        int netAddresBitValue = getBitValueByAddress(getNetAddress(address,
                subnetMask));
        int subnetMaskBitValue = getBitValueByAddress(subnetMask);
        int hostNumBitValue = ~subnetMaskBitValue;
        return getAddressByBitValue(netAddresBitValue | hostNumBitValue);
    }
    
    public static int getBitValueByAddress(byte[] address)
    {
        int value = address[3] & 0xff;
        value |= (address[2] << 8) & 0xff00;
        value |= (address[1] << 16) & 0xff0000;
        value |= (address[0] << 24) & 0xff000000;
        return value;
    }
    
    public static byte[] getAddressByString(String ipString)
    {
        String[] addressStrByte = ipString.split("\\.");
        byte[] address = new byte[4];
        for (int i = 0; i < address.length; i++)
        {
            address[i] = Short.valueOf(addressStrByte[i]).byteValue();
        }
        return address;
    }
    
    public static byte[] getSubnetMaskByMaskLength(short maskLength)
    {
        int m = 0;
        byte[] mask = new byte[4];
        for (int i = maskLength, j = 31; i > 0; i--, j--)
        {
            m |= 1 << j;
        }
        for (int i = mask.length; i > 0; i--)
        {
            mask[mask.length - i] = (byte) (m >> ((i - 1) << 3) & 0xff);
        }
        return mask;
    }
    
    public static String getIPStringByAddress(byte[] address)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= address.length; i++)
        {
            sb.append((short) (address[i - 1]) & 0xff);
            if (i != address.length)
            {
                sb.append('.');
            }
        }
        return sb.toString();
    }
    
    public static byte[] getAddressByBitValue(int bitValue)
    {
        byte[] address = new byte[4];
        for (int i = 0; i < address.length; i++)
        {
            address[address.length - i - 1] = (byte) (bitValue >> (i << 3) & 0xff);
        }
        return address;
    }
    
}
