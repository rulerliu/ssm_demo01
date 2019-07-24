package com.liuwq;

import org.apache.log4j.Logger;

public class TestLog4j {

    private static Logger logger = Logger.getLogger(TestLog4j.class); // 获取logger实例

    public static void main(String[] args) {

        logger.debug("调试debug信息");
        logger.info("普通Info信息");
        logger.warn("警告warn信息");
        logger.error("error信息");
        logger.fatal("严重错误fatal信息");
    }
}