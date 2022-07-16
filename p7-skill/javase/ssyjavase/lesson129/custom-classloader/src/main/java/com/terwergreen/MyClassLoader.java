package com.terwergreen;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器
 *
 * @name: MyClassLoader
 * @author: terwer
 * @date: 2022-07-07 21:37
 **/
public class MyClassLoader extends ClassLoader {
    //  类加载器的名字
    private String name;

    // 加载类的路径
    private String path = "/home/terwer/Downloads";

    // class文件的扩展名
    private final String fileType = ".class";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyClassLoader(String name) {
        // 让系统类加载器成为类加载器的父类加载器
        super();
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        // 显式指定类的父类加载器
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            name = name.replace(".", "/");
            is = new FileInputStream(path + "/" + name + fileType);

            baos = new ByteArrayOutputStream();

            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }

            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return data;
    }
}
