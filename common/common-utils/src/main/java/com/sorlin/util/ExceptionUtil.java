package com.sorlin.util;/**
 * @author 李松岭
 * @date 2020-07-25 13:17
 **/

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @program: guli-parent
 * @description: 错误详情输出工具类
 * @author: sorlin
 * @create: 2020-07-25 13:17
 */
public class ExceptionUtil {
    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
