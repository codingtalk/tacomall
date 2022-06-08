/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-29 17:09:14
 * @LastEditTime: 2022-01-03 16:51:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/req/tm/TmStaffAddReq.java
 */
package store.tacomall.apiadmin.valid.tm;

import com.alibaba.fastjson.annotation.JSONField;
import store.tacomall.apiadmin.annotation.EnumValidator;
import store.tacomall.apiadmin.enums.tm.TmStaffStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TmStaffAddValid {

    @NotNull(message = "部门ID不能为空")
    private Integer deptId;

    @NotNull(message = "岗位ID不能为空")
    private Integer jobId;

    @JSONField(name = "accessExtraJson")
    @NotBlank(message = "权限json不能为空")
    private String accessExtraJson;

    @JSONField(name = "username")
    @NotBlank(message = "账户不能为空")
    private String username;

    @JSONField(name = "mobile")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @JSONField(name = "passwd")
    @NotBlank(message = "密码不能为空")
    private String passwd;

    @JSONField(name = "nickname")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @EnumValidator(value = TmStaffStatusEnum.class, message = "状态, 1: ok; 2: error")
    private Integer status;

}
