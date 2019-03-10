package org.liezi.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * IPAddressUtil
 */
public class IPAddressUtil {
    private static final String X_REAL_IP = "X-Real-IP";
    private static final String X_FORWARDED_FOR = "x-forwarded-for" ;
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP" ;
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP" ;
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP" ;
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR" ;
    private static final String UNKNOWN = "unknown" ;
    private static final String DEFAULT_IP = "127.0.0.1";
    /**
     * 通过HttpServletRequest返回IP地址
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(final HttpServletRequest request) {
        String ip = request.getHeader(X_REAL_IP);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            String ips = request.getHeader(X_FORWARDED_FOR);
            if(ips !=null && ips.length()>0){
                int index = ips.indexOf(",");
                ip= ips.substring(0,index);
            }else{
                ip = ips;
            }
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip =  request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = DEFAULT_IP;
        }
        return ip;
    }
}
