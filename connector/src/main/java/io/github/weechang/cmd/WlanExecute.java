package io.github.weechang.cmd;

import io.github.weechang.config.Command;
import io.github.weechang.config.FileConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * wlan 命令行执行器
 */
public class WlanExecute {

    /**
     * 校验WLAN配置文件是否正确
     */
    public static boolean check(String ssid, String profileName) {
        System.out.println("check : " + profileName);
        try {
            if (addProfile(profileName)) {
                if (connect(ssid)) {
                    Thread.sleep(1000L);
                    if (ping()) {
                        return true;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 执行器
     *
     * @param cmd 命令
     */
    private static List<String> execute(String cmd, String filePath) {
        Process process = null;
        List<String> result = new ArrayList<String>();
        try {
            if (filePath != null) {
                process = Runtime.getRuntime().exec(cmd, null, new File(filePath));
            } else {
                process = Runtime.getRuntime().exec(cmd);
            }
            BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            String line = null;
            while ((line = bReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加配置文件
     *
     * @param profileName 添加配置文件
     */
    private static boolean addProfile(String profileName) {
        String cmd = Command.ADD_PROFILE.replace("FILE_NAME", profileName);
        List<String> result = execute(cmd, FileConfig.PROFILE_PATH);
        if (result != null && result.size() > 0) {
            if (result.get(0).contains("添加到接口")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 连接wifi
     *
     * @param ssid 添加配置文件
     */
    private static boolean connect(String ssid) {
        boolean connected = false;
        String cmd = Command.CONNECT.replace("SSID_NAME", ssid);
        List<String> result = execute(cmd, null);
        if (result != null && result.size() > 0) {
            if (result.get(0).contains("已成功完成")) {
                connected = true;
            }
        }
        return connected;
    }

    /**
     * ping 百度
     */
    private static boolean ping() {
        boolean pinged = false;
        String cmd = "ping www.baidu.com";
        List<String> result = execute(cmd, null);
        if (result != null && result.size() > 0) {
            for (String item : result) {
                if (item.contains("来自")) {
                    pinged = true;
                    break;
                }
            }
        }
        return pinged;
    }
}
