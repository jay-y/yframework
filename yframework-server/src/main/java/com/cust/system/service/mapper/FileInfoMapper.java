package com.cust.system.service.mapper;

import com.cust.system.domain.FileInfo;
import com.cust.system.service.dto.FileInfoDTO;
import org.mapstruct.Mapper;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Author: zk
 */

@Mapper(componentModel = "spring")
public interface FileInfoMapper extends AuditingEntityMapper<FileInfo, FileInfoDTO>
{
    FileInfoDTO doToDto(FileInfo fileInfo);

    List<FileInfoDTO> dosToDtos(List<FileInfo> dos);

    FileInfo dtoToDo(FileInfoDTO dto);

    List<FileInfo> dtosToDos(List<FileInfoDTO> dtos);

    default FileInfo doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        return fileInfo;
    }

}
