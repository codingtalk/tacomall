/***
 * @Author: 码上talk|RC
 * @Date: 2021-04-30 14:15:00
 * @LastEditTime: 2021-10-11 22:02:52
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/vo/base/PageVo.java
 * @微信: 13680065830
 * @邮箱: 3189482282@qq.com
 * @oops: Just do what I think it is right
 */

package store.tacomall.common.vo.base;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PageVo extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  @Override
  public Object put(String key, Object value) {
    if (value != null) {
      String type = value.getClass().getSimpleName();
      if (type.equals("Timestamp")) {
        Timestamp times = (Timestamp) value;
        LocalDateTime time = times.toLocalDateTime();
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return super.put(key, format);
      } else {
        return super.put(key, value);
      }
    }
    return super.put(key, value);
  }
}
