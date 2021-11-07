package com.kapcb.framework.web.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title: BasePageResult </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BasePageResult <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ApiModel(value = "分页返回值实体对象", description = "分页返回值实体对象")
public class BasePageResult<T> implements IPageResult<T>, Serializable {

    private static final long serialVersionUID = 5794340994984498956L;

    @ApiModelProperty(value = "当前页数")
    private long pageNum;

    @ApiModelProperty(value = "当前每页显示条数")
    private long pageSize;

    @ApiModelProperty(value = "总条数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private long totalPage;

    @ApiModelProperty(value = "分页数据")
    private List<T> records;
}
