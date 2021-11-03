package store.tacomall.jobadmin.core.alarm;

import store.tacomall.jobadmin.core.model.XxlJobInfo;
import store.tacomall.jobadmin.core.model.XxlJobLog;

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
