package io.github.weechang.cmd;

import java.util.concurrent.Callable;

/**
 * @author zhangwei
 * date 2018/10/22
 * time 14:47
 */
public class CheckTask  implements Callable<Boolean> {

    private String ssid;
    private String password;

    public CheckTask(String ssid, String password) {
        this.ssid = ssid;
        this.password = password;
    }

    public Boolean call() {
        WlanExecute execute = new WlanExecute();
        boolean checked = execute.check(ssid, password);
        return checked;
    }
}
