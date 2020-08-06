package com.mrxyc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class TestClass {

    public static void main(String[] args) {

        System.out.println(getPhraseSimilarity("附近的卓美亚酒店", "卓美亚"));
    }

    public static double getPhraseSimilarity(String src, String des) {

        final double YUZHI = 0;

        Set<String> srcSet = new HashSet<String>();
        Set<String> desSet = new HashSet<String>();
        for (int i = 0; i < src.length(); i++) {
            srcSet.add(String.valueOf(src.charAt(i)));
        }
        for (int i = 0; i < des.length(); i++) {
            desSet.add(String.valueOf(des.charAt(i)));
        }

        Set<String> unionSet = new HashSet<>(srcSet.size() + desSet.size());
        unionSet.addAll(srcSet);
        unionSet.addAll(desSet);

        Map<String, double[]> unionMap = new HashMap<String, double[]>();

        for (String index : unionSet) {
            double[] weight = {YUZHI, YUZHI};
            if (src.contains(index)) {
                weight[0] = 1;
            }
            if (des.contains(index)) {
                weight[1] = 1;
            }
            unionMap.put(index, weight);
        }

        //开始计算，百分比
        Iterator<String> it = unionMap.keySet().iterator();
        double s1 = 0, s2 = 0, Ssum = 0;  //S1、S2
        while (it.hasNext()) {
            double[] c = unionMap.get(it.next());
            Ssum += c[0] * c[1];
            s1 += c[0] * c[0];
            s2 += c[1] * c[1];
        }
        //百分比
        return Ssum / Math.sqrt(s1 * s2);

    }

    public static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int index = partition(nums, low, high, low);
            quickSort(nums, low, index - 1);
            quickSort(nums, index + 1, high);
        }
    }

    public static int partition(int[] nums, int low, int high, int pivotIndex) {
        swap(nums, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high - 1; i++) {
            if (nums[i] < nums[high]) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, high);
        return storeIndex;
    }

    public static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    public static void test11() throws Exception {
        //location_xy,fullPYALL,productId,weight,fullPY,preFields,type,title,storeFileds,accurateFields,name,searchSTDName,isCampaign,searchFields,id,addressPY
        //id,isCampaign,name,type,weight,searchSTDName,preFields,accurateFields,searchFields,storeFileds,productId,addressPY,fullPY,fullPYALL
        //1459 11 13 15用#  8用~~
        File file = new File("C:\\Users\\yanchun.xu\\Desktop\\new9");
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("\t");
            if (split.length < 2) {
                continue;
            }
            stringBuffer.append("update opt_theme set ");
            stringBuffer.append("final_adr_url =");
            stringBuffer.append("'" + split[2] + "'");
            stringBuffer.append(",final_ios_url =");
            stringBuffer.append("'" + split[2] + "'");
            stringBuffer.append(",adr_url =");
            stringBuffer.append("'" + split[2] + "'");
            stringBuffer.append(",ios_url =");
            stringBuffer.append("'" + split[2] + "'");
            stringBuffer.append(" where id =");
            stringBuffer.append(split[0]);
            stringBuffer.append(";");
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer.toString());
    }


    public static String toSolrIndexString4QueryProcessor(String original) {
        StringBuilder idxStr = new StringBuilder(original.toLowerCase(Locale.ENGLISH));
        int i = 0;
        while (i < idxStr.length()) {
            char c = idxStr.charAt(i);
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (c == '+' || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || Strings.isCJKCharacter(ub)
                    || Strings.isJapaneseKana(c) || Strings.isKorean(ub) || ub == Character.UnicodeBlock.CYRILLIC) {
                idxStr.setCharAt(i, Strings.traditional2Simplified(c));
                ++i;
            } else {
                idxStr.deleteCharAt(i);
            }
        }
        return idxStr.toString();
    }

    public static String toSolrIndexString(String original) {
        if (original == null) {
            return "";
        }
        original = original.replaceAll("丶|\\|", "");
        StringBuilder idxStr = new StringBuilder(original.toLowerCase(Locale.ENGLISH));
        int i = 0;
        while (i < idxStr.length()) {
            char c = idxStr.charAt(i);
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (c == '+' || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || Strings.isCJKCharacter(ub)
                    || Strings.isJapaneseKana(c) || Strings.isKorean(ub) || ub == Character.UnicodeBlock.CYRILLIC) {
                idxStr.setCharAt(i, Strings.traditional2Simplified(Strings.chineseNumber2Number(c)));
                ++i;
            } else {
                idxStr.deleteCharAt(i);
            }
        }
        return idxStr.toString();
    }


    private void testError() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            e = new NullPointerException("11");
            Exception e1 = new NullPointerException("");
            System.out.println(e.getMessage());
            System.out.println(e1.getMessage());
            e = new ArrayIndexOutOfBoundsException("");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getClass());
        }
    }

    private double pointMulti(double[] d1, double[] d2) {
        double res = 0;
        if (d1.length == d2.length) {
            for (int i = 0; i < d1.length; i++) {
                res += d1[i] * d2[i];
            }
        }
        return res;
    }

    private double sqrtMulti(double[] doubles) {
        double res = 0;
        for (double d : doubles) {
            res += Math.pow(d, 2);
        }
        res = Math.sqrt(res);
        return res;
    }

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void testQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.offer("2"));
        queue.remove();
        System.out.println(queue.peek());
    }

    public static void testReplaceURLParam() {
        //        Map<String, String> param = new HashMap<>();
//        param.put("id", "1");
//        param.put("cat", URLEncoder.encode("in_track=a_sy_search_menpiao_63", "utf-8"));
//        System.out.println(replaceURLParam("://sight/detail?id=16165&cat=in_track%3da_sy_search_menpiao_62", param));
//        System.out.println(replaceURLParam("://react/open?hybridId=finance_center_rn&moduleName=finance_center_rn&initProps=%7B%22param%22%3A%7B%22from%22%3A%22guanjcsearch%22%7D%7D", param));

    }

    /**
     * 替换url参数 中文参数需要自己encode
     */
    public static String replaceURLParam(String url, Map<String, String> params) throws UnsupportedEncodingException {
        String res = "";
//        if (Strings.isNullOrEmpty(url)) {
//            return res;
//        }
        //rn
        if (url.contains("://react/open?hybridId=")) {
            int paramStart = url.indexOf("&initProps=");
            if (paramStart < 0) {
                paramStart = url.indexOf("?initProps=");
            }
            int paramEnd = url.indexOf("&", paramStart + 1);
            String param = "";
            String valueJson = "";
            if (paramEnd > 0) {
                param = url.substring(paramStart, paramEnd);
                valueJson = url.substring(paramStart + 11, paramEnd);
            } else {
                param = url.substring(paramStart);
                valueJson = url.substring(paramStart + 11);
            }
            String decode = URLDecoder.decode(valueJson, "utf-8");
            String replace = "&initProps=" + decode;
            res = url.replace(param, replace);
        } else {
            //非rn需要考虑 hy 和 普通的
            //主要区分在于&和%26
            if (url.contains("://hy?")) {
                int urlStart = url.indexOf("&url=");
                if (urlStart < 0) {
                    urlStart = url.indexOf("?url=");
                }
                int urlEnd = url.indexOf("&", urlStart + 1);
                String httpEncodeUrl = "";
                String httpUrl = "";
                if (urlEnd > 0) {
                    httpEncodeUrl = url.substring(urlStart, urlEnd);
                } else {
                    httpEncodeUrl = url.substring(urlStart);
                }
                httpUrl = URLDecoder.decode(httpEncodeUrl, "utf-8");
                res = url.replace(httpEncodeUrl, URLEncoder.encode(simpleUrlReplaceParam(httpUrl, params), "utf-8"));
            } else {
                res = simpleUrlReplaceParam(url, params);
            }
        }
        return res;
    }

    public static String simpleUrlReplaceParam(String url, Map<String, String> params) {
        for (String key : params.keySet()) {
            int paramStart = url.indexOf("&" + key + "=");
            if (paramStart < 0) {
                paramStart = url.indexOf("?" + key + "=");
            }
            int paramEnd = url.indexOf("&", paramStart + 1);
            if (paramStart > 0) {
                String param = "";
                if (paramEnd > 0) {
                    param = url.substring(paramStart + 1, paramEnd);
                } else {
                    param = url.substring(paramStart + 1);
                }
                url = url.replace(param, key + "=" + params.get(key));
            } else {
                if (url.contains("?")) {
                    url = url + "&" + key + "=" + params.get(key);
                } else {
                    url = url + "?" + key + "=" + params.get(key);
                }
            }
        }
        return url;
    }

    public static void bigDecimalToString() {
        BigDecimal ba = new BigDecimal(String.valueOf("0.880"));
        BigDecimal multiply = ba.multiply(new BigDecimal("10"));
        System.out.println(multiply.stripTrailingZeros().toPlainString());
    }


    public static void testJsEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("nashorn");
        try {
            engine.eval("function f_b5c27578a9b5655153392368de1d978a(x){ var y=JSON.parse(x);y.newPlatform=true; return JSON.stringify(y)}");
            System.out.println(engine.invokeFunction("f_b5c27578a9b5655153392368de1d978a", "{}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
