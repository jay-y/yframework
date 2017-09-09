package org.yframework.mybatis.auditing.service.mapper;

import java.util.List;

/**
 * Description: AuditingEntityMapper.<br>
 * Date: 2017/9/8 15:55<br>
 * Author: ysj
 */
public interface AuditingEntityMapper<DO, DTO>
{
    DTO doToDto(DO user);

    List<DTO> dosToDtos(List<DO> dos);

    DO dtoToDo(DTO dto);

    List<DO> dtosToDos(List<DTO> dtos);

    DO doFromId(String id);
}
