package com.mrxyc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class OptSqlClass {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\yanchun.xu\\Desktop\\opt");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "utf-8"));
        String line = null;
        StringBuilder sql = new StringBuilder();

        while ((line = br.readLine()) != null) {
            String[] split = line.split("\t");
            String querys = split[0];
            String til = String.format("关于%s的旅行笔记", querys);
            String adrurl = "://react/open?hybridId=t_mavericks_rn&pageName=SearchResultPage&initProps=" + Strings.urlEncode("{\"param\":{\"keyWords\":\"" + querys + "\",\"sort\":0,\"bz_trace\":\"great_search\",\"bz_source\":\"great_search_suggestion\"},\"isQRCTShareStore\":true}");
            String iosurl = adrurl;
            String title = til;
            String queries = querys;
            String ios_url = iosurl;
            String min_iosvid = "80011190";
            String max_iosvid = "80099999";
            String adr_url = adrurl;
            String min_adrvid = "60001293";
            String max_adrvid = "60099999";
            String touch_url = "";
            String valid_time = "2019-12-19~2020-01-19";
            String exist_status = "1";
            String config_type = "schema";
            String final_adr_url = adrurl;
            String final_ios_url = iosurl;
            String final_touch_url = "";
            String validity_location = "联想词";
            String deploy_status = "2";
            String show_index = "3";
            String support_search_btn = "0";
            String support_accurate_match = "1";
            String busi_type = "content";
            String block_suggestion = "0";
            sql(sql, title, queries, ios_url, touch_url, min_iosvid, max_iosvid, adr_url, min_adrvid, max_adrvid, valid_time, exist_status, config_type, final_adr_url, final_ios_url, final_touch_url, validity_location, deploy_status, show_index, support_search_btn, support_accurate_match, busi_type, block_suggestion);
        }


        System.out.println(sql.toString());
    }

    private static void sql(StringBuilder sql, String title, String queries, String ios_url, String touch_url, String min_iosvid, String max_iosvid, String adr_url, String min_adrvid, String max_adrvid, String valid_time, String exist_status, String config_type, String final_adr_url, String final_ios_url, String final_touch_url, String validity_location, String deploy_status, String show_index, String support_search_btn, String support_accurate_match, String busi_type, String block_suggestion) {
        sql.append("INSERT INTO opt_theme (title,queries,ios_url,touch_url,min_iosvid,max_iosvid,adr_url,min_adrvid,max_adrvid,valid_time,exist_status,config_type" +
                ",final_adr_url,final_ios_url,final_touch_url,validity_location,deploy_status,show_index,support_search_btn,support_accurate_match,busi_type,block_suggestion) VALUES(");
        sql.append("'" + title + "'");
        sql.append(",'" + queries + "'");
        sql.append(",'" + ios_url + "'");
        sql.append(",'" + touch_url + "'");
        sql.append(",'" + min_iosvid + "'");
        sql.append(",'" + max_iosvid + "'");
        sql.append(",'" + adr_url + "'");
        sql.append(",'" + min_adrvid + "'");
        sql.append(",'" + max_adrvid + "'");
        sql.append(",'" + valid_time + "'");
        sql.append(",'" + exist_status + "'");
        sql.append(",'" + config_type + "'");
        sql.append(",'" + final_adr_url + "'");
        sql.append(",'" + final_ios_url + "'");
        sql.append(",'" + final_touch_url + "'");
        sql.append(",'" + validity_location + "'");
        sql.append(",'" + deploy_status + "'");
        sql.append(",'" + show_index + "'");
        sql.append(",'" + support_search_btn + "'");
        sql.append(",'" + support_accurate_match + "'");
        sql.append(",'" + busi_type + "'");
        sql.append(",'" + block_suggestion + "'");
        sql.append(");");
        sql.append("\n");
    }
}
