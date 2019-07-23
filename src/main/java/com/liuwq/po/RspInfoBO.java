package com.liuwq.po;

import com.liuwq.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * 所有HSF接口返回的基类 <br>
 * 标题: <br>
 * 描述: <br>
 * 公司: www.tydic.com<br>
 *
 * @author LiuChang
 * @time 2017年7月18日 下午6:22:57
 */
@ApiModel(value = "返回结果")
public class RspInfoBO<T> implements Serializable {

    private static final long serialVersionUID = -9139965062655369886L;

    /**
     * 返回编码
     */
    @ApiModelProperty(value = "返回码")
    private String respCode;

    /**
     * 返回编码
     */
    @ApiModelProperty(value = "返回码描述")
    private String respDesc;

    private Map<String, Object> map;
    @ApiModelProperty(value = "数据")
    private T data;

    public RspInfoBO() {
        this.respCode = Constants.RESPCODE_SUCCESS;
        this.respDesc = Constants.RESPCODE_SUCCESS_NAME;
    }

    public RspInfoBO(T obj) {
        this.respCode = Constants.RESPCODE_SUCCESS;
        this.respDesc = Constants.RESPCODE_SUCCESS_NAME;
        data = obj;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public static RspInfoBO success() {
        RspInfoBO result = new RspInfoBO();
        result.setRespCode(Constants.RESPCODE_SUCCESS);
        result.setRespDesc(Constants.RESPCODE_SUCCESS_NAME);
        return result;
    }

    public static RspInfoBO success(String msg) {
        RspInfoBO result = new RspInfoBO();
        result.setRespCode(Constants.RESPCODE_SUCCESS);
        result.setRespDesc(msg);
        return result;
    }

    public static RspInfoBO error(String msg) {
        RspInfoBO result = new RspInfoBO();
        result.setRespCode(Constants.RESPCODE_ERROR);
        result.setRespDesc(msg);
        return result;
    }

    public static RspInfoBO error(String code, String msg) {
        RspInfoBO result = new RspInfoBO();
        result.setRespCode(code);
        result.setRespDesc(msg);
        return result;
    }

    public boolean isSuccess() {
        return Constants.RESPCODE_SUCCESS.equals(this.respCode) ? true : false;
    }
    public boolean isError() {
        return !isSuccess();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RspInfoBO [respCode=" + respCode + ", respDesc=" + respDesc + "]";
    }

}
