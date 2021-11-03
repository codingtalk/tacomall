/***
 * @Author: 码上talk|RC
 * @Date: 2021-05-15 13:49:48
 * @LastEditTime: 2021-10-06 14:57:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/FileUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import java.io.File;
import java.io.InputStream;

public class FileUtil {

  public static InputStream getResourcesFileInputStream(String fileName) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
  }

  public static String getPath() {
    return FileUtil.class.getResource("/").getPath();
  }

  public static File createNewFile(String pathName) {
    File file = new File(getPath() + pathName);
    if (file.exists()) {
      file.delete();
    } else {
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
    }
    return file;
  }

  public static File readFile(String pathName) {
    return new File(getPath() + pathName);
  }

  public static File readUserHomeFile(String pathName) {
    return new File(System.getProperty("user.home") + File.separator + pathName);
  }
}