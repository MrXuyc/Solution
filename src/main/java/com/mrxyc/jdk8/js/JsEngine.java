package com.mrxyc.jdk8.js;

import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class JsEngine {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("nashorn");
        try {
            engine.eval("function f_b5c27578a9b5655153392368de1d978a(x){ return \"1\\2\\3\".replace(\"\\\\\",\"\")}");
            System.out.println(engine.invokeFunction("f_b5c27578a9b5655153392368de1d978a", "88.0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//   js调用java
//    var BigDecimal = Java.type('java.math.BigDecimal');
//
//    function calculate(amount, percentage) {
//
//        var result = new BigDecimal(amount).multiply(
//                new BigDecimal(percentage)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN);
//
//        return result.toPlainString();
//    }
//
//    var result = calculate(568000000000000000023,13.9);
//    print(result);
}
