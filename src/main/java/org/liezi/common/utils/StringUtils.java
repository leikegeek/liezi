package org.liezi.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @Author: Lake
 * @Date: 2018/7/15 21:21
 * @Description:String工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static Logger logger = LogManager.getLogger(StringUtils.class);

    private static final char UNDERLINE = '_';
    /**
     * 驼峰转下划线工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String param) {
        if (isNotEmpty(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isNotEmpty(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (c == 95) {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }
    /**
     * 实体字段转表字段
     * @author: lake.lei
     * @date: 2018/8/6 10:38
     * @param: [fields 实体字段]
     * @return: java.lang.String[]
     */
    public static String[] entityFieldToDB(String[] fields){
        if(null == fields || fields.length<1){
            return fields;
        }
        String[] fieldArray = new String[fields.length];
        for(int i=0,len=fields.length;i<len;i++){
            fieldArray[i] = StringUtils.upperCase(camelToUnderline(fields[i]));
        }
        return fieldArray;
    }
    /**
     * 获取LIST的参数
     * @param list
     * @author: lake.lei
     * @date: 2019/2/28 17:53
     * @return: java.lang.String
     */
    public static String getListString(List<String> list) {
        String result = "";
        for (String s : list) {
            result += s + " ";
        }
        return result;
    }
    /**
     * TODO 临时处理办法
     * 获取当前用户
     * @author: lake.lei
     * @date: 2019/3/1 15:50
     * @return: java.lang.String
     */
    public static String currentAccount(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String currentAccount =  (String)session.getAttribute("ad");
            return currentAccount;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取session值
     * @param request
     * @return
     */
    public static String getSessionValue(ServletRequest request,String key){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String token = (String) session.getAttribute(key);
        return token;
    }

    /**
     * 解密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                logger.error("key is null");
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                logger.error("Key长度不是16位");
                return null;
            }
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decode(content);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                logger.error(e.toString());
                return null;
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return null;
        }

    }

}
