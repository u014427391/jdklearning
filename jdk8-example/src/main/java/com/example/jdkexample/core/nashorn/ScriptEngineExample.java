package com.example.jdkexample.core.nashorn;


import cn.hutool.json.JSONUtil;
import com.example.jdkexample.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.script.*;
import java.io.*;

@Slf4j
public class ScriptEngineExample {

    public static void main(String[] args)  throws Exception{

        testAdd();
        testJson();
        testJsFile();
        testInvokeFunction();
        testBinding();
    }


    private static void testAdd() throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
        String foo = "1+2";
        System.out.println(scriptEngine.eval(foo));
    }

    private static void testJson() throws ScriptException {
        String script = "var json ={\"name\":\"admin\" , \"mobiTel\":\"123\"};" +
                "var result ={};result.name=json.name;result.mobiTel=json.mobiTel;JSON.stringify(result);";

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
        Object obj = scriptEngine.eval(script);
        User user = JSONUtil.toBean(obj.toString() , User.class);
        System.out.println(user.toString());
    }

    private static void testJsFile()  {
        try (Reader reader= new FileReader(new File(ScriptEngineExample.class.getClassLoader().getResource("test.js").getFile()))){
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
            scriptEngine.eval(reader);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException：{}" , e);
        } catch (IOException e) {
            log.error("IOException：{}" , e);
        } catch (ScriptException e){
            log.error("ScriptException：{}" , e);
        }
    }


    private static void testInvokeFunction() throws ScriptException, NoSuchMethodException {
        String script = "var json ={\"name\":\"admin\" , \"mobiTel\":\"123\"};" +
                "function jsonStringify(){ var result ={};result.name=json.name;result.mobiTel=json.mobiTel;print(JSON.stringify(result));}";
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
        scriptEngine.eval(script);
        Invocable invocable = (Invocable) scriptEngine;
        invocable.invokeFunction("jsonStringify");
    }

    private static void testBinding() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
        Bindings bindings = new SimpleBindings();
        bindings.put("username","admin");
        scriptEngine.eval("print('hello , ' + username);" , bindings);
    }

}
