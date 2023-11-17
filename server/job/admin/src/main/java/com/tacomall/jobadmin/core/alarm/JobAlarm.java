package com.tacomall.jobadmin.core.alarm;

import com.tacomall.jobadmin.core.model.XxlJobInfo;
import com.tacomall.jobadmin.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
