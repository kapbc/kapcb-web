package com.kapcb.web.struct;

import com.kapcb.web.model.page.BasePageResult;
import com.kapcb.web.model.page.IPageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <a>Title: PageStruct </a>
 * <a>Author: Kapcb <a>
 * <a>Description: PageStruct <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/11/7 14:41
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageStruct {

    PageStruct INSTANCE = Mappers.getMapper(PageStruct.class);

}
