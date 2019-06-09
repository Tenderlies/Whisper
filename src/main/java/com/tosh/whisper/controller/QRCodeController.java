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
package com.tosh.whisper.controller;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tosh.whisper.utils.QRCodeUtil;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ClassName: QRCodeController<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 *
 * @author taoshuang
 * @since 2016年10月30日
 */
@Controller
@Path("/")
public class QRCodeController {

    @GET
    @Path(value = "qrcode")
    @Produces("image/png")
    public Response getQRCode(@QueryParam("content") String content)
            throws WriterException, IOException {
        content = "".equals(content) ? "https://tenderlies.github.io" : content;
        BitMatrix bitMatrix = QRCodeUtil.getQRCode(content);

        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {

                MatrixToImageWriter.writeToStream(bitMatrix, "png",
                        output);
                output.flush();
            }
        };
        return Response.ok(stream).build();
    }
}
