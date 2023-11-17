package com.tacomall.apiweb.vo.page;

import java.util.List;

import com.tacomall.common.entity.approve.ApproveType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageApproveListVo {

    List<ApproveType> approveTypeList;
}
