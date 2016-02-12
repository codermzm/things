package com.things.common.path;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.lang.StringUtils;


public class PathUtils {

    private static String webRootPath = StringUtils.EMPTY;
    
    private static String configPath = StringUtils.EMPTY;
    
    public static final String testResource = "$testResource$"; 

    private static File getPath(File[] files) {
        File[] arrayOfFile = files;
        int j = files.length;
        for (int i = 0; i < j; i++) {
            File file = arrayOfFile[i];
            if ((file.isDirectory())
                && (file.getName().equalsIgnoreCase("web-inf"))
                && (!file.getParentFile().getName().equals("jsp-demo"))) {
                return file;
            }
        }

        arrayOfFile = files;
        j = files.length;
        for (int i = 0; i < j; i++) {
            File file = arrayOfFile[i];
            if (file.isDirectory()) {
                File temfile = getPath(file.listFiles());
                if (temfile != null) {
                    return temfile;
                }
            }
        }
        return null;
    }

    /**
     * 合并路径 
     * @param paths 路径
     * @return 合并后的路径
     * */
    public static String combine(String... paths) {
        if (paths.length == 0) {
            return "";
        }

        File combined = new File(paths[0]);

        int i = 1;
        while (i < paths.length) {
            combined = new File(combined, paths[i]);
            ++i;
        }

        return combined.getPath();
    }

    
    /**
     * 获取测试资源文件路径
     * */
    public static String getProjectPath(){
        String result = StringUtils.EMPTY;
        URL url = PathUtils.class.getResource("/");
        File baseDir = new File(url.getFile());
        do{
            if (baseDir != null && !baseDir.exists() || !baseDir.isDirectory()){
                break ;
            }
            IOFileFilter fileFilter1 =  new NameFileFilter(testResource);
            FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, DirectoryFileFilter.DIRECTORY);
            
            if(baseDir.list(filenameFilter).length > 0){
                result = baseDir.getPath();
                break;
            }else {
                baseDir = baseDir.getParentFile();
            }
            
        }while(StringUtils.isEmpty(result));
       
        return result;
    }
    
    /**
     * 获取测试资源根路径
     * */
    public static String getTestSourcePath(){
        String rootPath = PathUtils.getProjectPath();
        
        return FilenameUtils.concat(rootPath, PathUtils.testResource);
    }
    
    public static String getConfigPath(){
        String webRoot = getWebRoot();
        if(!StringUtils.isEmpty(webRoot))
            configPath = FilenameUtils.concat(webRoot, "WEB-INF/config");

        return configPath;
    }
    
    public static String getWebRoot(){
        if(StringUtils.isEmpty(webRootPath)){
            String webRoot = PathUtils.getWebRootPath();
            if(!StringUtils.isEmpty(webRoot))
                webRootPath = webRoot;
        }
        if(StringUtils.isEmpty(webRootPath)){
            String patn = PathUtils.getTestSourcePath();
            webRootPath = patn;
        }
        
        return webRootPath;
    }
    
    private static String getWebRootPath() {
        String webRoot = new String();

        File webinfFile = null;
        URL classesUrl = PathUtils.class.getClassLoader().getResource("/");
        if (classesUrl == null) {
            classesUrl = PathUtils.class.getClassLoader().getResource("");
        }
        File classesFile = new File(classesUrl.getFile());
        if (("classes".equals(classesFile.getName()))
            && (classesFile.getParentFile() != null)
            && ("WEB-INF".equalsIgnoreCase(classesFile.getParentFile()
                .getName()))) {
            webinfFile = classesFile.getParentFile();
        } else if ((classesFile.getParentFile() != null)
            && ("lib".equals(classesFile.getParentFile().getName()))
            && (classesFile.getParentFile().getParentFile() != null)
            && ("WEB-INF".equalsIgnoreCase(classesFile.getParentFile()
                .getParentFile().getName()))) {
            webinfFile = classesFile.getParentFile().getParentFile();
        } else {
            File rootFile = new File(new File("").getAbsolutePath());
            webinfFile = getPath(rootFile.listFiles());
        }
        if (webinfFile != null) {
            webRoot = webinfFile.getParentFile().getPath();
            if (webRoot.startsWith("file:\\")) {
                webRoot = webRoot.substring(6);
            }
            if (webRoot.startsWith("file:\\\\")) {
                webRoot = webRoot.substring(7);
            }
            if (webRoot.startsWith("file:/")) {
                webRoot = webRoot.substring(5);
            }
            if (webRoot.startsWith("file://")) {
                webRoot = webRoot.substring(6);
            }
        }

        return webRoot;
    }
    
}
