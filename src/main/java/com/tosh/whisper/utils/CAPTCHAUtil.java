/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2016年10月25日
 */
package com.tosh.whisper.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Arrays;
import java.util.Random;

/**
 * ClassName: CAPTCHAUtil<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2016年10月25日
 */
public class CAPTCHAUtil
{
    private static Random random = new Random();
    
    public static RenderedImage getCAPTCHA(String catpcha)
    {
        return getCAPTCHA(120, 45, catpcha);
    }
    
    public static RenderedImage getCAPTCHA(Integer width, Integer height,
            String catpcha)
    {
        BufferedImage im = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = im.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++)
        {
            colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
            fractions[i] = random.nextFloat();
        }
        Arrays.sort(fractions);
        // 设置边框色
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, width, height);
        Color c = getRandColor(200, 250);
        // 设置背景色
        g2.setColor(c);
        g2.fillRect(0, 2, width, height - 4);
        
        // 绘制干扰线
        // 设置线条的颜色
        g2.setColor(getRandColor(160, 200));
        for (int i = 0; i < 20; i++)
        {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        
        // 添加噪点
        // 噪声率
        float yawpRate = 0.05f;
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            im.setRGB(x, y, rgb);
        }
     // 使图片扭曲
        shear(g2, width, height, c);
        
        g2.setColor(getRandColor(100, 160));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = catpcha.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(
                    Math.PI / 4 * random.nextDouble()
                            * (random.nextBoolean() ? 1 : -1),
                    (width / chars.length) * i + fontSize / 2, height / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((width - 10) / chars.length) * i + 5,
                    height / 2 + fontSize / 2 - 10);
        }
        g2.dispose();
        return im;
    }
    
    /**
     * 获取给定范围获得随机颜色
     *
     * @author taoshuang
     * @see 相关函数,对于重要的函数建议注释
     * @since 2016年10月25日
     * @param fc 偏小的值，不符合要求返回黑色
     * @param bc 偏大的值，不符合要求返回黑色
     * @return
     */
    private static Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if (fc > 255)
        {
            fc = 255;
        }
        if (bc > 255)
        {
            bc = 255;
        }
        if (bc <= fc)
        {
            return new Color(0, 0, 0);
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    private static int getRandomIntColor()
    {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb)
        {
            color = color << 8;
            color = color | c;
        }
        return color;
    }
    
    private static int[] getRandomRgb()
    {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++)
        {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }
    
    private static void shear(Graphics g, int w1, int h1, Color color)
    {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }
    
    private static void shearX(Graphics g, int w1, int h1, Color color)
    {
        
        int period = random.nextInt(2);
        
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);
        
        for (int i = 0; i < h1; i++)
        {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                            + (6.2831853071795862D * (double) phase)
                            / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
        
    }
    
    private static void shearY(Graphics g, int w1, int h1, Color color)
    {
        
        int period = random.nextInt(40) + 10; // 50;
        
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++)
        {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                            + (6.2831853071795862D * (double) phase)
                            / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap)
            {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }
}
