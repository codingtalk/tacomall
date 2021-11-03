/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 16:43:37
 * @LastEditTime: 2021-10-10 16:44:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/job/src/java/store/tacomall/job/handler/DemoHandler.java
 */
package store.tacomall.jobexecutor.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DemoHandler {
  private static Logger logger = LoggerFactory.getLogger(DemoHandler.class);

  @XxlJob("demoHandler")
  public void demoJobHandler() throws Exception {
    XxlJobHelper.log("XXL-JOB, Hello World.");
    for (int i = 0; i < 5; i++) {
      XxlJobHelper.log("beat at:" + i);
      TimeUnit.SECONDS.sleep(2);
    }
  }

}
