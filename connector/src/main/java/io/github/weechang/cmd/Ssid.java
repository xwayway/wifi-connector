package io.github.weechang.cmd;

/**
 * SSID 封装
 * @author zhangwei
 * date 2018/10/22
 * time 15:59
 */
public class Ssid {

    /**
     * 名称
     */
    private String name;
    /**
     * 加密方式
     */
    private String auth;
    /**
     * 信号强度
     */
    private int dB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getdB() {
        return dB;
    }

    public void setdB(int dB) {
        this.dB = dB;
    }
}
