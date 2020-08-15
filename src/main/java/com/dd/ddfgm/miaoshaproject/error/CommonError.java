package com.dd.ddfgm.miaoshaproject.error;

/**
 * 2020-08-15 12:19:59.
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);


}
