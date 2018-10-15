package com.cust.system.service.mapper;

import com.cust.system.domain.Office;
import com.cust.system.service.dto.OfficeDTO;
import org.mapstruct.Mapper;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Author: zk
 */

@Mapper(componentModel = "spring")
public interface OfficeMapper extends AuditingEntityMapper<Office, OfficeDTO>
{
    OfficeDTO doToDto(Office office);

    List<OfficeDTO> dosToDtos(List<Office> dos);

    Office dtoToDo(OfficeDTO dto);

    List<Office> dtosToDos(List<OfficeDTO> dtos);

    default Office doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        Office office = new Office();
        office.setId(id);
        return office;
    }

}
