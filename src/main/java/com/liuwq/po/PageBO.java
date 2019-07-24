package com.liuwq.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "Page",
        description = "分页"
)
public class PageBO {
    @ApiModelProperty(
            value = "页码",
            example = "1"
    )
    private int page = 1;
    @ApiModelProperty(
            value = "每页显示数量",
            example = "10"
    )
    private int rows = 10;

    public PageBO() {
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
