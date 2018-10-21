package io.github.weechang.generetor;

import io.github.weechang.config.FileConfig;
import io.github.weechang.config.Generetor;
import io.github.weechang.config.Password;
import io.github.weechang.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

import static io.github.weechang.config.Generetor.FIRST_MOST_COUPLE;

/**
 * 密码生成器
 */
public class PasswordGeneretor {

    /**
     * 生成密码
     *
     * @param generetor 生成方式
     */
    public static void genPassword(Generetor generetor) {
        if (generetor.equals(Generetor.MOST_USE)) {
            mostUseGen();
        } else if (generetor.equals(FIRST_MOST_COUPLE)) {
            firstMostCouple();
        }
    }

    /**
     * 生成常用密码
     */
    private static void mostUseGen() {
        FileUtils.appendToFile(FileConfig.getPasswordPath(Generetor.MOST_USE), Password.MOST_USE);
        System.out.println("常用密码生成完毕");
    }

    /**
     * 姓名首拼 + 常用组合
     */
    public static void firstMostCouple() {
        // 两个字首拼
        List<String> twoWords = new ArrayList<String>();
        // 三个字首拼
        List<String> threeWords = new ArrayList<String>();
        for (int x = 0; x < Password.HAN.length; x++) {
            String one = Password.HAN[x];
            for (int y = 0; y < Password.HAN.length; y++) {
                String two = Password.HAN[y];
                twoWords.add(one + two);
                for (int z = 0; z < Password.HAN.length; z++) {
                    String three = Password.HAN[z];
                    threeWords.add(one + two + three);
                }
            }
        }

        List<String> twoPass = connectList(twoWords, Password.MOST_COUPLE);
        FileUtils.appendToFile(FileConfig.getPasswordPath(FIRST_MOST_COUPLE), twoPass.toArray(new String[twoPass.size()]));
        System.out.println("两字姓名+常用组合已生成");

        List<String> threePass = connectList(threeWords, Password.MOST_COUPLE);
        FileUtils.appendToFile(FileConfig.getPasswordPath(FIRST_MOST_COUPLE), twoPass.toArray(new String[threePass.size()]));
        System.out.println("三字姓名+常用组合已生成");
    }

    private static List<String> connectList(List<String> outer, String... inner) {
        List<String> result = new ArrayList<String>();
        for (String outerText : outer) {
            for (String innerTxt : inner) {
                String pwd = outerText + innerTxt;
                if (pwd != null && pwd.length() > 7) {
                    result.add(pwd);
                }
            }
        }
        return result;
    }

    /**
     * 获取指定类型的指定行数的密码
     *
     * @param generetor 指定类型
     * @param start     开始行号
     * @param end       结束行号
     * @return 密码
     */
    public static List<String> getPassword(Generetor generetor, int start, int end) {
        String filePath = getFilePath(generetor);
        return FileUtils.readLine(filePath, start, end);
    }

    private static String getFilePath(Generetor generetor) {
        String filePath = null;
        switch (generetor) {
            case MOST_USE:
                filePath = FileConfig.getPasswordPath(Generetor.MOST_USE);
                break;
            case FIRST_MOST_COUPLE:
                filePath = FileConfig.getPasswordPath(FIRST_MOST_COUPLE);
                break;
            default:
                filePath = FileConfig.getPasswordPath(Generetor.MOST_USE);
                break;
        }
        return filePath;
    }
}
