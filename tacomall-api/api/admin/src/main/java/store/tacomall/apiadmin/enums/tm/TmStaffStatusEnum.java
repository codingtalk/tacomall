package store.tacomall.apiadmin.enums.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TmStaffStatusEnum {

    OK(1), ERROR(2),;

    private Integer code;
}