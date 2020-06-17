package com.example.lic.reflect.ioc;

import org.springframework.util.StringUtils;
import org.testng.collections.Lists;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描包下路径
 * 包括本地文件和jar包文件
 * @author ljb
 *
 */
public class ScanningFile {

    //接口类class 用于过滤 可以不要
    private Class<?> superStrategy = String.class;

    private List<Class<?>> eleStrategyList = new ArrayList<>();

    //默认使用的类加载器
    private ClassLoader classLoader = ScanningFile.class.getClassLoader();

    //private static final String STARATEGY_PATH = "com.example.lic.reflect.ioc";//需要扫描的策略包名

    public static void main(String[] args) {
        ScanningFile s = new ScanningFile();
        s.getClass();
    }

    /**
     * 获取包下所有实现了superStrategy的类并加入list
     */
    public List<Class<?>> getClass(final String path){
        URL url = classLoader.getResource(path.replace(".", "/"));
        String protocol = url.getProtocol();
        List<Class<?>> result = Lists.newArrayList();
        if ("file".equals(protocol)) {
            // 本地自己可见的代码
            findClassLocal(path, result);
        } else if ("jar".equals(protocol)) {
            // 引用jar包的代码
            findClassJar(path, result);
        }

        return result;
    }

    /**
     * 本地查找
     * @param packName
     */
    private void findClassLocal(final String packName, List<Class<?>> result) {
        URI url = null;
        try {
            url = classLoader.getResource(packName.replace(".", "/")).toURI();
        } catch (URISyntaxException e1) {
            throw new RuntimeException("未找到策略资源");
        }

        File file = new File(url);
        file.listFiles(chiFile -> {
            if(chiFile.isDirectory()){
                if (StringUtils.isEmpty(packName)) {
                    findClassLocal(chiFile.getName(), result);
                } else {
                    findClassLocal(packName + "." + chiFile.getName(), result);
                }
            }
            if(chiFile.getName().endsWith(".class")){
                Class<?> clazz = null;
                try {
                    clazz = classLoader.loadClass(packName + "." + chiFile.getName().replace(".class", ""));
                    result.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(chiFile);
                if(superStrategy.isAssignableFrom(clazz)){
                    eleStrategyList.add(clazz);
                }
                return true;
            }
            return false;
        });
    }

    /**
     * jar包查找
     * @param packName
     */
    private void findClassJar(final String packName, List<Class<?>> result){
        String pathName = packName.replace(".", "/");
        JarFile jarFile  = null;
        try {
            URL url = classLoader.getResource(pathName);
            JarURLConnection jarURLConnection  = (JarURLConnection )url.openConnection();
            jarFile = jarURLConnection.getJarFile();
        } catch (IOException e) {
            throw new RuntimeException("未找到策略资源");
        }

        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName();

            if(jarEntryName.contains(pathName) && !jarEntryName.equals(pathName+"/")){
                //递归遍历子目录
                if(jarEntry.isDirectory()){
                    String clazzName = jarEntry.getName().replace("/", ".");
                    int endIndex = clazzName.lastIndexOf(".");
                    String prefix = null;
                    if (endIndex > 0) {
                        prefix = clazzName.substring(0, endIndex);
                    }
                    findClassJar(prefix, result);
                }
                if(jarEntry.getName().endsWith(".class")){
                    Class<?> clazz = null;
                    try {
                        clazz = classLoader.loadClass(jarEntry.getName().replace("/", ".").replace(".class", ""));
                        result.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(superStrategy.isAssignableFrom(clazz)){
                        eleStrategyList.add((Class<? extends String>) clazz);
                    }
                }
            }

        }

    }

}