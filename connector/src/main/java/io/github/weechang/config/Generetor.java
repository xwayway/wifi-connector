package io.github.weechang.config;

/**
 * 密码生成器配置
 */
public enum Generetor {

    /**
     * 弱口令
     * eg: 88888888
     */
    WAKE,
    /**
     * 常用密码
     * eg: 88888888
     */
    MOST_USE,
    /**
     * 首字母+常用组合
     * eg：zs123456
     */
    FIRST_MOST_COUPLE,
    /**
     * 首字母+常用组合+常用符号
     * eg：zs123456!
     */
    FIRST_MOST_COUPLE_MOST_INS,
    /**
     * 首字母大写+常用组合
     * eg：Zs123456
     */
    FIRST_UPPER_MOST_COUPLE,
    /**
     * 首字母大写+常用组合+常会用符号
     * eg：Zs123456
     */
    FIRST_UPPER_MOST_COUPLE_MOST_INS,
    /**
     * 姓名全拼+年
     * eg：zhangsan1994
     */
    NAME_YEAR,
    /**
     * 首字母+出生年月
     * eg: zs19940109
     */
    FIRST_BIRTHDAY
    ;
}
