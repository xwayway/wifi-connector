package io.github.weechang.generator;

import io.github.weechang.Connector;
import io.github.weechang.config.Profile;
import io.github.weechang.util.FileUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 配置文件生成器
 */
public class ProfileGenerator {

    private String ssid = null;
    private String passwordPath = null;
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public ProfileGenerator(String ssid, String passwrodPath) {
        this.ssid = ssid;
        this.passwordPath = passwrodPath;
    }

    /**
     * 生成配置文件
     */
    public void genProfile() {
        List<String> passwordList = null;
        int counter = 0;
        outer:
        while (true) {
            int start = counter * Connector.BATH_SIZE;
            int end = (counter + 1) * Connector.BATH_SIZE - 1;
            passwordList = FileUtils.readLine(passwordPath, start, end);
            if (passwordList != null && passwordList.size() > 0) {
                // 生成配置文件
                for (String password : passwordList) {
                    GenThread genThread = new GenThread(ssid, password);
                    threadPool.execute(genThread);
                }
            } else {
                break outer;
            }
            counter++;
        }
    }
}

class GenThread implements Runnable {

    private String ssid = null;
    private String password = null;

    GenThread(String ssid, String password) {
        this.ssid = ssid;
        this.password = password;
    }

    public void run() {
        String profileContent = Profile.PROFILE.replace(Profile.WIFI_NAME, ssid);
        profileContent = profileContent.replace(Profile.WIFI_PASSWORD, password);
        FileUtils.writeToFile(Connector.PROFILE_TEMP_PATH + "\\" + password + ".xml", profileContent);
    }
}
