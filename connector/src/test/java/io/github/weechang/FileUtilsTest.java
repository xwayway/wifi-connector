package io.github.weechang;


import io.github.weechang.config.Generetor;
import io.github.weechang.generetor.PasswordGeneretor;

import java.util.List;

public class FileUtilsTest {

    public static void main(String[] args) {
        List<String> read1 = PasswordGeneretor.getPassword(Generetor.WAKE, 0, 100);
        List<String> read2 = PasswordGeneretor.getPassword(Generetor.WAKE, 100, 200);

        for (String str : read1){
            if (read2.contains(str)){
                System.out.println(str);
            }
        }
    }
}

