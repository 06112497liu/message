/**
 * BrandBigData.com Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.bbd.message.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文件工具类
 * @author chenshiwei
 * @version $Id: FileUtils.java, v 0.1 2016/9/11 17:04 chenshiwei Exp $
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static File getDirFromClassPath(String basePath) {
        System.out.println("basePath : "+basePath);
        File basePathDir = null;
        URL cache = FileUtils.class.getClassLoader().getResource(basePath);
        if (null == cache) {
            System.out.println("cache is null");
            URL resource = FileUtils.class.getClassLoader().getResource("");
            basePathDir = new File(resource.getFile(), basePath);
        } else {
            System.out.println("cache is not null");
            basePathDir = new File(cache.getFile());
        }

        if (!basePathDir.exists()) {
            basePathDir.mkdirs();
        }
        return basePathDir;
    }

    public static File getFile(File basePathDir, String name) throws IOException {
        File file = new File(basePathDir, name);
        if (!file.exists()) {
            return null;
        }
        return file;
    }
}
