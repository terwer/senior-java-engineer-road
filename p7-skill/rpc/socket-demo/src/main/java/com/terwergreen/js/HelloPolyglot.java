package com.terwergreen.js;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

/**
 * https://www.graalvm.org/22.1/reference-manual/js/
 *
 * @name: HelloPolyglot
 * @author: terwer
 * @date: 2022-05-10 02:45
 **/
public class HelloPolyglot {

    static String JS_CODE = "(function myFun(param){console.log('hello '+param);})";

    public static void main(String[] args) {
        System.out.println("Hello Java!");
        try (Context context = Context.create()) {
            Value value = context.eval("js", JS_CODE);
            value.execute(args[0]);
        }
    }
}