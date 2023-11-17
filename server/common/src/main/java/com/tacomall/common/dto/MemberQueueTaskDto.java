package com.tacomall.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberQueueTaskDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int type;

}
