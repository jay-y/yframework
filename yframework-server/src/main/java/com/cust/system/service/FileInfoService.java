package com.cust.system.service;

import com.cust.system.domain.FileInfo;
import com.cust.system.repository.FileInfoRepository;
import com.cust.system.service.dto.FileInfoDTO;
import com.cust.system.service.mapper.FileInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;

/**
 * Author: zk
 */
@Service
@Transactional(readOnly = true)
public class FileInfoService extends AbstractAuditingEntityService<FileInfo, FileInfoDTO>
{
    private final FileInfoMapper fileInfoMapper;
    private final FileInfoRepository fileInfoRepository;

    public FileInfoService(FileInfoMapper fileInfoMapper, FileInfoRepository fileInfoRepository)
    {
        this.fileInfoMapper = fileInfoMapper;
        this.fileInfoRepository = fileInfoRepository;
    }

    @Override
    public FileInfoRepository getRepository()
    {
        return fileInfoRepository;
    }

    @Override
    public FileInfoMapper getMapper()
    {
        return fileInfoMapper;
    }
}
