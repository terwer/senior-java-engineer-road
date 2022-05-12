package com.terwergreen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * @name: UnZip
 * @author: terwer
 * @date: 2022-05-12 15:48
 **/
public class UnZip {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/terwer/Downloads/temp.zip";
        if (args.length > 0) {
            fileName = args[0];
        }
        unzip(fileName);
        System.out.println("解压完毕.");
    }

    /**
     * 解压zip压缩包
     *
     * @param fileName
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    private static void unzip(String fileName) throws IOException {
        String filePath = fileName.substring(0,
                fileName.lastIndexOf(File.separator) + 1);
        ZipFile zipFile = new ZipFile(fileName, "GBK");
        Enumeration emu = zipFile.getEntries();
        try {
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();
                if (entry.getName().contains("..\\")
                        || entry.getName().contains("../")) {
                    throw new RuntimeException(new Exception("error file name: " + entry.getName()));
                }
                Boolean isWindows = System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
                if (entry.isDirectory()) {
                    if (isWindows) {
                        new File(filePath + entry.getName()).mkdirs();
                    } else {
                        // entry.setUnixMode(ZipEntry.PLATFORM_UNIX);
                        makeDirectory(new File(filePath), entry.getName());
                    }
                    continue;
                }

                BufferedInputStream bis = new BufferedInputStream(
                        zipFile.getInputStream(entry));
                File file = null;
                if (isWindows) {
                    file = new File(filePath + entry.getName());
                } else {
                    file = makeFile(new File(filePath), entry.getName());
                }
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int count;
                byte[] data = new byte[1024];
                try {
                    while ((count = bis.read(data, 0, 1024)) != -1) {
                        bos.write(data, 0, count);
                    }
                    bos.flush();
                } finally {
                    if (bos != null) {
                        bos.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                }
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }

    }

    private static void makeDirectory(File destination, String fileName) {
        String[] dirs = fileName.split("\\\\");
        if (dirs == null) {
            return;
        }

        String path = "";
        for (String dir : dirs) {
            path = path + File.separator + dir;
            new File(destination, path).mkdir();
        }
    }

    private static File makeFile(File destination, String name)
            throws IOException {
        String[] dirs = name.split("\\\\");
        if (dirs == null) {
            return null;
        }

        String path = "";
        int size = dirs.length;

        if (size == 1) {
            return new File(destination, name);
        } else if (size > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                path = path + File.separator + dirs[i];
                new File(destination, path).mkdir();
            }

            path = path + File.separator + dirs[dirs.length - 1];
            File f = new File(destination, path);

            f.createNewFile();
            return f;
        } else {
            return null;
        }
    }
}
