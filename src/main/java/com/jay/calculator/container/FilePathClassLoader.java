package com.jay.calculator.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilePathClassLoader {

    public static List<String> getFileList() throws IOException {
        List<String> fileList = new ArrayList<>();
        URL pathUrl = ClassLoader.getSystemResource("");
        boolean needDealWithJar = pathUrl == null;
        if (needDealWithJar) {
            fileList = getFileListInJar();
        }else {
            fileList=getFileListOutOfJar(pathUrl);
        }
        return fileList;
    }

    private static List<String> getFileListOutOfJar(URL pathUrl) {
        List<String> fileList = new ArrayList<>();
        String path = pathUrl.getPath();
        File file = new File(path);
        iterateGetFileList(fileList, file);
        return fileList;
    }

    private static List<String> getFileListInJar() throws IOException {
        List<String> classNames = new ArrayList<String>();
        String path = FilePathClassLoader.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        ZipInputStream zip = new ZipInputStream(new FileInputStream(path));
        for (
                ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory()) {
                // This ZipEntry represents a class. Now, what class does it represent?
                String className = entry.getName().replace('/', '.'); // including ".class"
                classNames.add(className);
            }
        }
        return classNames;
    }

    private static void iterateGetFileList(List<String> fileList, File file) {
        boolean fileIsDir = file.isDirectory();
        if (fileIsDir) {
            File subFiles[] = file.listFiles();
            for (File detailSubFile : subFiles) {
                iterateGetFileList(fileList, detailSubFile);
            }
        } else {
            fileList.add(getFileName(file.getAbsolutePath()));
        }
    }

    private static String getFileName(String path) {
        // replace all "/" or "\" to "."
        String rst = path.replaceAll("/", ".").replaceAll("\\\\", ".");
        String startStr = "classes";
        // get fileName
        int startIndex = rst.indexOf(startStr) + startStr.length() + 1;
        rst = rst.substring(startIndex);
        return rst;
    }
}
