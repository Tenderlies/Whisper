package com.tosh.whisper.controller;

import com.tosh.whisper.utils.CAPTCHAUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Controller
@Path("/")
public class CAPTCHAController {

    @GET
    @Path("captcha")
    @Produces("image/png")
    public Response getCAPTCHA(@Context HttpServletRequest request,
                               @Context HttpServletResponse response)
            throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Random random = new Random();
        String scodeLib = "ABCDEFGHJKLMNPQRESTUVWXYZabcdefghijklmnpqrstuvwxyz123456789";
        char[] codeLib = scodeLib.toCharArray();
        String code = "";

        for (int i = 0; i < 4; i++) {
            String temp = String
                    .valueOf(codeLib[random.nextInt(codeLib.length)]);
            code += temp;
        }
        code = code.toLowerCase();

        request.getSession().setAttribute("captcha", code);
        RenderedImage im = CAPTCHAUtil.getCAPTCHA(code);

        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                ImageIO.write(im, "png", output);
                output.flush();
            }
        };

        return Response.ok(stream).build();
    }
}
