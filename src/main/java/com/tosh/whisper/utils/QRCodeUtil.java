/**
 * Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 *
 * @author taoshuang
 * @version 1.0
 * @since 2016年10月30日
 */
package com.tosh.whisper.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * ClassName: QRCodeUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 *
 * @author taoshuang
 * @since 2016年10月30日
 */
public class QRCodeUtil {
    private static int defaultWidth = 300;
    private static int defaultHeight = 300;
    private static HashMap<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();
    private static HashMap<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>();

    static {
        encodeHints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        encodeHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        encodeHints.put(EncodeHintType.MARGIN, 1);

        decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        decodeHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        //decodeHints.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
    }

    public static BitMatrix getQRCode(String contents) throws WriterException {
        contents = contents == null ? "" : contents;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, defaultWidth, defaultHeight, encodeHints);

        return bitMatrix;
    }

    public static BitMatrix getQRCode(String contents, int width, int height)
            throws WriterException {
        contents = contents == null ? "" : contents;
        width = width > 50 ? width : defaultWidth;
        height = height > 50 ? height : defaultHeight;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, width, height, encodeHints);

        return bitMatrix;
    }

    public static String parseQRCode(InputStream imageStream) {
        try {
            BufferedImage image = ImageIO.read(imageStream);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            Result result = new MultiFormatReader().decode(binaryBitmap, decodeHints);

            return result.getText();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
