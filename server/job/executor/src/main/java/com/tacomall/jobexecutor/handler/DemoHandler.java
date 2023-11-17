package com.tacomall.jobexecutor.handler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoHandler {
    private static Logger logger = LoggerFactory.getLogger(DemoHandler.class);

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        logger.info("XXL-JOB, Hello World.");
    }

}
