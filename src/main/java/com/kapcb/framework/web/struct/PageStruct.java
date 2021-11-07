package com.kapcb.framework.web.struct;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

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
