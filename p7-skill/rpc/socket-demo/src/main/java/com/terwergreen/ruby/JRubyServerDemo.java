package com.terwergreen.ruby;

import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

/**
 * JRuby版的socket服务端
 *
 * @name: JRubyServerDemo
 * @author: terwer
 * @date: 2022-05-09 20:22
 **/
public class JRubyServerDemo {
    public static void main(String[] args) {
        ScriptingContainer ruby = new ScriptingContainer(LocalVariableBehavior.PERSISTENT);
        ruby.runScriptlet(PathType.ABSOLUTE, "./src/main/java/com/terwergreen/ruby/server_demo.rb");
    }
}
