package io.github.weechang.config;

/**
 * windows下常用命令
 */
public class Command {

    /**
     * 列出配置文件
     */
    public static final String SHOW_PROFILE = "netsh wlan show profile";

    /**
     * 导出配置文件
     */
    public static final String EXPORT_PRIFILE = "netsh wlan export profile key=clear";

    /**
     * 删除配置文件
     */
    public static final String DELETE_PROFILE = "netsh wlan delete profile name=FILE_NAME";

    /**
     * 添加配置文件
     */
    public static final String ADD_PROFILE = "netsh wlan add profile filename=FILE_NAME";

    /**
     * 连接wifi
     */
    public static final String CONNECT = "netsh wlan connect name=SSID_NAME";

    /**
     * 列出接口
     */
    public static final String SHOW_INTERFACE = "netsh wlan show interface";

    /**
     * 开启接口
     */
    public static final String INTERFACEC_ENABLE = "netsh interface set interface \"Interface Name\" enabled";

    /**
     * 列出所有可用wifi
     */
    public static final String SHOW_NETWORKS = "netsh wlan show networks mode=bssid";

}
