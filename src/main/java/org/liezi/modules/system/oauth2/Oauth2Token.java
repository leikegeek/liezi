package org.liezi.modules.system.oauth2;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * @author: lake.lei
 * @date: 2019-03-04
 * @description:token
 */
public class Oauth2Token implements AuthenticationToken {
    private String token;

    public Oauth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
