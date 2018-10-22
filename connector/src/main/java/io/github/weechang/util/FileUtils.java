package io.github.weechang.util;

import io.github.weechang.Connector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * 创建文件
     *
     * @param filePath 文件路径
     */
    public static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return;
            }
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("创建文件出错");
            String error = "写入文件出错:" + filePath;
            FileUtils.appendToFile(Connector.LOG_PATH, error);
            e.printStackTrace();
        }
    }

    /**
     * 将内容追加到文件末尾
     *
     * @param filePath 文件路径
     * @param contents 追加内容
     */
    public static void appendToFile(String filePath, String... contents) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                createFile(filePath);
            }
            fileWriter = new FileWriter(filePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String content : contents) {
                bufferedWriter.append(content + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将内容覆盖写入到文件中
     *
     * @param filePath 文件路径
     * @param contents 写入内容
     */
    public static void writeToFile(String filePath, String... contents) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            createFile(filePath);
        }
        appendToFile(filePath, contents);
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {

        }
    }

    /**
     * 读取指定行
     *
     * @param filePath 文件路径
     * @param start    开始行号
     * @param end      结束行号
     * @return 读取结果
     */
    public static List<String> readLine(String filePath, int start, int end) {
        List<String> result = new ArrayList<String>();
        int line = 0;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String lineStr = null;
            while ((lineStr = bufferedReader.readLine()) != null) {
                if (start <= line && line <= end) {
                    result.add(lineStr);
                } else if (line > end) {
                    break;
                }
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
