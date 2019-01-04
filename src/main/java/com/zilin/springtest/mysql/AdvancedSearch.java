package com.zilin.springtest.mysql;

import org.apache.ibatis.jdbc.SQL;


public class AdvancedSearch {
    public String select(String object){
        return new SQL() {
            {
                SELECT("*");
                FROM("springusers");
                String a = "("+object+"|1)+";
                WHERE("regexp_like(userName,'"+a+"')");
            }
        }.toString();
    }
}
