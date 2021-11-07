package com.kapcb.framework.web.util;

import cn.hutool.core.util.PageUtil;
import com.kapcb.framework.web.model.page.BasePageResult;
import com.kapcb.framework.web.model.page.BasePagination;
import com.kapcb.framework.web.model.page.IPageResult;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <a>Title: PageUtil </a>
 * <a>Author: Kapcb <a>
 * <a>Description: PageUtil <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 14:25
 */
@UtilityClass
public class PageResultUtil {

    public static <T, D> IPageResult<T> restPage(List<T> data, IPageResult<D> result) {
        IPageResult<T> pageResult = new BasePageResult<>();
        if (CollectionUtils.isNotEmpty(data)) {
            pageResult.setRecords(data);
            pageResult.setTotal(result.getTotal());
            pageResult.setPageNum(result.getPageNum());
            pageResult.setPageSize(result.getPageSize());
            pageResult.setTotalPage(result.getTotalPage());
        }
        return pageResult;
    }

    public static <T, D> IPageResult<T> restPage(List<T> data, long total, BasePagination pagination) {
        IPageResult<T> pageResult = new BasePageResult<>();
        if (Objects.nonNull(pagination)) {
            pageResult.setRecords(data);
            pageResult.setTotal(total);
            pageResult.setTotalPage(PageUtil.totalPage((int) total, (int) pagination.getPageSize()));
            convertPageParam(pageResult, pagination);
        }
        return pageResult;
    }

    public static <T> void convertPageParam(IPageResult<T> pageResult, BasePagination pagination) {
        if (Objects.nonNull(pagination)) {
            pageResult.setPageNum(pagination.getPageNum());
            pageResult.setPageSize(pagination.getPageSize());
        }
    }
}

