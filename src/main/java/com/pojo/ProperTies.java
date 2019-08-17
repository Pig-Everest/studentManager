package com.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProperTies {
    private static String path;
    private static String pageSize;

    public static String getPath() {
        return path;
    }
    @Value("${path}")
    public void setPath(String path) {
        this.path = path;
    }

    public static String getPageSize() {
        return pageSize;
    }
    @Value("${pageSize}")
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
