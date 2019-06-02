/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2016年10月30日
 */
package com.tosh.whisper.utils;

import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * ClassName: QRCodeUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2016年10月30日
 */
public class QRCodeUtil
{
    private static int defaultWidth = 300;
    private static int defaultHeight = 300;
    private static HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
    
    static
    {
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);
    }
    
    public static BitMatrix getQRCode(String contents) throws WriterException
    {
        contents = contents == null ? "" : contents;
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, defaultWidth, defaultHeight, hints);
        
        return bitMatrix;
    }
    
    public static BitMatrix getQRCode(String contents, int width, int height)
            throws WriterException
    {
        contents = contents == null ? "" : contents;
        width = width > 50 ? width : defaultWidth;
        height = height > 50 ? height : defaultHeight;
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, width, height, hints);
        
        return bitMatrix;
    }
}
