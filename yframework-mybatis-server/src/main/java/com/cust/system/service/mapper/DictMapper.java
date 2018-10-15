package com.cust.system.service.mapper;

import com.cust.system.domain.Dict;
import com.cust.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Author: zk
 */

@Mapper(componentModel = "spring")
public interface DictMapper extends AuditingEntityMapper<Dict, DictDTO>
{
    DictDTO doToDto(Dict dict);

    List<DictDTO> dosToDtos(List<Dict> dos);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Dict dtoToDo(DictDTO dto);

    List<Dict> dtosToDos(List<DictDTO> dtos);

    default Dict doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        Dict dict = new Dict();
        dict.setId(id);
        return dict;
    }

}
