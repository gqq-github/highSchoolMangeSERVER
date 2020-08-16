package cn.gq.android.core.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken {

    private String userId;
    private String authToken;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    private Integer role ;
    public StatelessToken(String userId, String authToken) {
        this.userId = userId;
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Object getPrincipal() {
       return userId;
    }

    @Override
    public Object getCredentials() {
        return authToken;
    }
}
