package com.terwergreen.python;

import org.python.util.PythonInterpreter;

/**
 * Jython版socket服务端
 *
 * @name: JythonServerDemo
 * @author: terwer
 * @date: 2022-05-09 14:04
 **/
public class JythonServerDemo {
    public static void main(String[] args) {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            // pyInterp.exec("print('hello')");
            pyInterp.execfile("./src/main/java/com/terwergreen/python/server_demo.py");
        }
    }
}
