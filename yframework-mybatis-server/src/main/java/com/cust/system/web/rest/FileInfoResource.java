package com.cust.system.web.rest;

import com.becypress.toolkit.Becypress;
import com.cust.system.service.FileInfoService;
import com.cust.system.service.dto.FileInfoDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yframework.mybatis.auditing.generator.IDGenerator;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/mgt/file-infos")
public class FileInfoResource extends AbstractAuditingEntityResource<FileInfoDTO>
{
    private final Logger log = LoggerFactory.getLogger(FileInfoResource.class);
    private final FileInfoService fileInfoService;
    private final PasswordEncoder passwordEncoder;

    public FileInfoResource(FileInfoService fileInfoService, PasswordEncoder passwordEncoder)
    {
        this.fileInfoService = fileInfoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public FileInfoService getService()
    {
        return fileInfoService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<List<FileInfoDTO>> upload(@RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
    {
        List<FileInfoDTO> dtos = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0)
        {
            //循环获取file数组中得文件
            for (MultipartFile file : files)
            {
                FileInfoDTO dto = new FileInfoDTO();
                // 判断文件是否为空
                if (!file.isEmpty())
                {
                    try
                    {
                        String fileName = file.getOriginalFilename();
                        String storeName = IDGenerator.UUID.generate();
                        String[] contentType = file.getContentType().split("/");
                        String format = contentType[1];
                        String url = "/content/upload/" + format + "/" + storeName + "." + format;
                        // 文件保存路径
                        String ctxPath = request.getSession().getServletContext().getRealPath("/");
                        String filePath = ctxPath + url;
                        // 转存文件
                        this.copyFileCover(file.getInputStream(), filePath, true);
                        String fileMD5 = DigestUtils.md5Hex(new FileInputStream(filePath));
                        boolean isUpdate = false;
                        dto.setId(fileMD5);
                        dto.setMd5(fileMD5);
                        dto = fileInfoService.findOne(dto);
                        if (null == dto)
                        {
                            dto = new FileInfoDTO();
                            dto.setId(fileMD5);
                            dto.setMd5(fileMD5);
                        }
                        else
                        {
                            isUpdate = true;
                            Becypress.UTIL.extra().file().deleteFile(dto.getPath());
                        }
                        dto.setFileName(fileName);
                        dto.setStoreName(storeName);
                        dto.setUrl(url);
                        dto.setPath(filePath);
                        if (isUpdate)
                        {
                            dto = fileInfoService.update(dto);
                        }
                        else
                        {
                            dto = fileInfoService.insert(dto);
                        }
                        dtos.add(dto);
                    }
                    catch (Exception e)
                    {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
        return ResponseEntity.ok(dtos);
    }

    private boolean copyFileCover(InputStream ins, String descFileName, boolean coverlay)
    {
        File descFile = new File(descFileName);
        if (descFile.exists())
        {
            if (!coverlay)
            {
                log.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }

            log.debug("目标文件已存在，准备删除!");
            if (!Becypress.UTIL.extra().file().deleteFiles(descFileName))
            {
                log.debug("删除目标文件 " + descFileName + " 失败!");
                return false;
            }
        }
        else if (!descFile.getParentFile().exists())
        {
            log.debug("目标文件所在的目录不存在，创建目录!");
            if (!descFile.getParentFile().mkdirs())
            {
                log.debug("创建目标文件所在的目录失败!");
                return false;
            }
        }

        FileOutputStream outs = null;
        try
        {
            outs = new FileOutputStream(descFile);
            byte[] e = new byte[1024];

            int read;
            while ((read = ins.read(e)) != -1)
            {
                outs.write(e, 0, read);
            }

            log.debug("复制文件到" + descFileName + "成功!");
            return true;
        }
        catch (Exception e)
        {
            log.debug("复制文件失败：" + e.getMessage());
            return false;
        }
        finally
        {
            if (outs != null)
            {
                try
                {
                    outs.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (ins != null)
            {
                try
                {
                    ins.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
}
