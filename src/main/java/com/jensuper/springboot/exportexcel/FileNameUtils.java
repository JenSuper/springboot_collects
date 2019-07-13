/**
 * All rights Reserved, Designed By www.rongdasoft.com 
 * @version V1.0
 * @Title: FileNameUtils.java
 * @Description:
 * @author: hecong
 * @date: 2019年5月8日
 * @Copyright: 2019年5月8日 www.rongdasoft.com Inc. All rights reserved.
 */
package com.jensuper.springboot.exportexcel;

import com.google.common.base.Strings;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.UnaryOperator;

/**
 * @ClassName: FileNameUtils
 * @Description: 下载文件名处理
 * @author hecong
 * @date 2019年5月8日
 */

public class FileNameUtils {

    /**
     * @param request
     * @param fileName
     * @return
     * @Description 根据不同的浏览器进行对应处理
     * @author hecong
     * @author zhangyq 抽象出公共部分，加入chrome浏览器encode操作
     * @date 2019年5月8日 下午1:54:24
     */
    public static String encoding(HttpServletRequest request, String fileName) {
        return encode(request, fileName, (x) -> {
            try {
                return new String(x.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        });
    }

    /**
     * @param request
     * @param fileName
     * @return java.lang.String
     * @Description 根据不同的浏览器进行对应处理 对google,火狐浏览器进行encode处理
     * @Author zhangyq
     * @Date 2019/5/12
     **/
    public static String encodingWithChromeEncode(HttpServletRequest request, String fileName) {
        return encode(request, fileName, (x) -> new String(x.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

    }

    /**
     * @param request
     * @param fileName
     * @param function
     * @return java.lang.String
     * @Description 处理文件名
     * @Author zhangyq
     * @Date 2019/5/12
     **/
    private static String encode(HttpServletRequest request, String fileName, UnaryOperator<String> function) {
        try {
            Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
            String info = browser.getName().toLowerCase();
            // IE浏览器和火狐
            final String userAgent = request.getHeader("USER-AGENT");
            if ((info.contains("internet") && info.contains("explorer"))) {
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (info.contains("microsoft ")) {
                // microsoft edge浏览器
                fileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            } else {
                if (userAgent.contains("Mozilla") || info.contains("firefox")) {// google,火狐浏览器
                    fileName = function.apply(fileName);
                } else {
                    fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 检验文件格式
     * @param docType
     * @param newDocType
     * @return
     */
    public static Boolean verificationFileFormat(String docType,String newDocType){
        if(Strings.isNullOrEmpty(docType)){
            return false;
        }
        if(Strings.isNullOrEmpty(newDocType)){
            return false;
        }

        docType = docType.toLowerCase();

        newDocType = newDocType.toLowerCase();

        if(docType.equals(newDocType)){
            return true;
        }

        String docFileType = "doc,docx";
        if(docFileType.indexOf(docType) != -1){
            if(docFileType.indexOf(newDocType) != -1){
                return true;
            }
            return false;
        }

        String xlsFileType = "xls,xlsx";
        if(xlsFileType.indexOf(docType) != -1){
            if(xlsFileType.indexOf(newDocType) != -1){
                return true;
            }
            return false;
        }

        String pptFileType = "ppt,pptx";
        if(pptFileType.indexOf(docType) != -1){
            if(pptFileType.indexOf(newDocType) != -1){
                return true;
            }
            return false;
        }
        return false;
    }
}
