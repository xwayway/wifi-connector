package io.github.weechang.config;

/**
 * 文件配置
 */
public class FileConfig {

    public static final String PROFILE_PATH = "D:\\wlan\\profile";

    public static final String PASSWORD_PATH = "D:\\wlan\\password";

    public static final String WAKE_TXT = "wake.txt";

    public static final String MOST_USE_TXT = "most_use.txt";

    public static final String FIRST_MOST_COUPLE_TXT = "fist_most_couple.txt";

    public static final int PASSWORD_SIZE = 100;

    public static final String getPasswordPath(Generetor generetor) {
        String end;
        switch (generetor) {
            case WAKE:
                end = WAKE_TXT;
                break;
            case MOST_USE:
                end = MOST_USE_TXT;
                break;
            case FIRST_MOST_COUPLE:
                end = FIRST_MOST_COUPLE_TXT;
                break;
            default:
                end = MOST_USE_TXT;
                break;

        }
        return PASSWORD_PATH + "\\" + end;
    }
}

