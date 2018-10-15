package com.cust.system.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cust.system.service.DictService;
import com.cust.system.service.dto.DictDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;

import java.util.List;


@RestController
@RequestMapping("/mgt/dicts")
public class DictResource extends AbstractAuditingEntityResource<DictDTO>
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DictService dictService;

    public DictResource(DictService dictService)
    {
        this.dictService = dictService;
    }

    @Override
    public DictService getService()
    {
        return dictService;
    }

    @GetMapping("/type/{type}")
    @Timed
    public ResponseEntity<List<DictDTO>> all4Type(@PathVariable String type)
    {
        log.debug("REST request to get all by type: {}", type);
        List<DictDTO> list = dictService.findAllByType(type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
