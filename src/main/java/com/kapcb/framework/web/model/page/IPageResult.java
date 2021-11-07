package com.kapcb.framework.web.model.page;

import java.util.List;

/**
 * <a>Title: IPage </a>
 * <a>Author: Kapcb <a>
 * <a>Description: IPage <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 14:27
 */
public interface IPageResult<T> {

    long getPageNum();

    long getPageSize();

    long getTotal();

    long getTotalPage();

    List<T> getRecords();

    void setPageNum(long pageNum);

    void setPageSize(long pageSize);

    void setTotal(long total);

    void setTotalPage(long totalPage);

    void setRecords(List<T> records);
}
