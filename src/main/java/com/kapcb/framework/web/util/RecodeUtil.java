package com.kapcb.framework.web.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kapcb.framework.common.constants.enums.IntegerPool;
import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.common.util.ThrowableUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * <a>Title: RecodeUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RecodeUtil <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/20 22:14
 */
@Slf4j
@UtilityClass
public class RecodeUtil {

    /**
     * create re code
     *
     * @param content             String
     * @param width               int
     * @param height              int
     * @param httpServletResponse HttpServletResponse
     */
    public static void createReCode(String content, int width, int height, HttpServletResponse httpServletResponse) {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        /**
         * 容错级别最高
         */
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        /**
         * 设置字符编码
         */
        hints.put(EncodeHintType.CHARACTER_SET, StringPool.CHARACTER_SET_UTF_8_LOWER.value());

        /**
         * 二维码空白区域,最小为0也有白边,只是很小,最小是6像素左右
         */
        hints.put(EncodeHintType.MARGIN, IntegerPool.ONE.value());

        try {
            /**
             * 读取文件转换为字节数组
             */
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage bufferedImage = toBufferedImage(bitMatrix);
            /**
             * 转换成png格式的IO流
             */
            ImageIO.write(bufferedImage, StringPool.IMAGE_SUFFIX_PNG.value(), httpServletResponse.getOutputStream());
        } catch (IOException | WriterException e) {
            log.error("create re code error, error message is : {}", ThrowableUtil.getStackTrace(e));
        }
    }

    /**
     * convert to buffer image
     *
     * @param bitMatrix BitMatrix
     * @return BufferedImage
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(i, j, bitMatrix.get(i, j) ? IntegerPool.RGB_0xFF0.value() : IntegerPool.RGB_0xFFF.value());
            }
        }
        return bufferedImage;
    }

}
