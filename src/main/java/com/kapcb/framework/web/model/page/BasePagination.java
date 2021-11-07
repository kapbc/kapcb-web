package com.kapcb.framework.web.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <a>Title: BasePagination </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BasePagination <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 13:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页请求参数", description = "分页请求参数")
public class BasePagination implements Serializable {

    private static final long serialVersionUID = -3347283810042206322L;

    @ApiModelProperty(value = "页数(默认值为1)")
    private long pageNum = 1L;

    @ApiModelProperty(value = "每页显示条数(默认值为10)")
    private long pageSize = 10L;
}
