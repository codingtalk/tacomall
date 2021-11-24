package store.tacomall.apiadmin.req.tm;

import com.alibaba.fastjson.annotation.JSONField;
import store.tacomall.apiadmin.annotation.EnumValidator;
import store.tacomall.apiadmin.enums.tm.TmStaffStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "职员添加", description = "职员添加Req")
public class TmStaffAddReq {

    @ApiModelProperty(value = "部门ID", example = "1")
    @NotNull(message = "部门ID不能为空")
    private Integer deptId;

    @ApiModelProperty(value = "岗位ID", example = "1")
    @NotNull(message = "岗位ID不能为空")
    private Integer jobId;

    @ApiModelProperty(value = "权限json", example = "{}")
    @JSONField(name = "accessExtraJson")
    @NotBlank(message = "权限json不能为空")
    private String accessExtraJson;

    @ApiModelProperty(value = "账户", example = "root")
    @JSONField(name = "username")
    @NotBlank(message = "账户不能为空")
    private String username;

    @ApiModelProperty(value = "手机号", example = "16607574271")
    @JSONField(name = "mobile")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码", example = "123456")
    @JSONField(name = "passwd")
    @NotBlank(message = "密码不能为空")
    private String passwd;

    @ApiModelProperty(value = "昵称", example = "E123456")
    @JSONField(name = "nickname")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "状态，1：ok，2：error", example = "1")
    @EnumValidator(value = TmStaffStatusEnum.class, message = "状态, 1: ok; 2: error")
    private Integer status;

}
