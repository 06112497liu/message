/**
 * BrandBigData.com Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.bbd.message.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文件工具类
 * @author chenshiwei
 * @version $Id: FileUtils.java, v 0.1 2016/9/11 17:04 chenshiwei Exp $
 */
public class FileUtils {

    public static File getDirFromClassPath(String basePath) {
        File basePathDir = null;
        URL cache = FileUtils.class.getClassLoader().getResource(basePath);
        if (null == cache) {
            URL resource = FileUtils.class.getClassLoader().getResource("");
            basePathDir = new File(resource.getFile(), basePath);
        } else {
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
