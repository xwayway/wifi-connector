package io.github.weechang.config;

/**
 * wifi 配置文件
 */
public class Profile {

    /**
     * wifi 名称
     */
    public static final String WIFI_NAME = "WIFI_NAME";

    /**
     * wifi 密码
     */
    public static final String WIFI_PASSWORD = "WIFI_PASSWORD";


    /**
     * 配置文件
     */
    public static final String PROFILE = "<?xml version=\"1.0\"?>\n" +
            "<WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">\n" +
            "    <name>WIFI_NAME</name>\n" +
            "    <SSIDConfig>\n" +
            "        <SSID>\n" +
            "            <name>" + WIFI_NAME + "</name>\n" +
            "        </SSID>\n" +
            "    </SSIDConfig>\n" +
            "    <connectionType>ESS</connectionType>\n" +
            "    <connectionMode>auto</connectionMode>\n" +
            "    <MSM>\n" +
            "        <security>\n" +
            "            <authEncryption>\n" +
            "                <authentication>WPA2PSK</authentication>\n" +
            "                <encryption>AES</encryption>\n" +
            "                <useOneX>false</useOneX>\n" +
            "            </authEncryption>\n" +
            "            <sharedKey>\n" +
            "                <keyType>passPhrase</keyType>\n" +
            "                <protected>false</protected>\n" +
            "                <keyMaterial>" + WIFI_PASSWORD + "</keyMaterial>\n" +
            "            </sharedKey>\n" +
            "        </security>\n" +
            "    </MSM>\n" +
            "    <MacRandomization xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v3\">\n" +
            "        <enableRandomization>false</enableRandomization>\n" +
            "    </MacRandomization>\n" +
            "</WLANProfile>";
}
