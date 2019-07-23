package com.liuwq.constants;


public interface Constants {

    String RESPCODE_SUCCESS = "0000";
    String RESPCODE_SUCCESS_NAME = "操作成功";
    String RESPCODE_ERROR = "0001";
    Integer LOCK_EXPIRE = 3;
    Long DAYTIME = 86400L;

    //活动状态
    int ACTIVITY_STATUS_APPLY = 0; //地市申请
    int ACTIVITY_STATUS_APPLY_SUCCESS = 1; //审核通过
    int ACTIVITY_STATUS_UPDATE = 2; //修改
    int ACTIVITY_STATUS_DELETE = 9; //作废
}
