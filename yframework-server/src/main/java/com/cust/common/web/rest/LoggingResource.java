package com.cust.common.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: LoggingResource.<br>
 * Date: 2017/7/31 00:54<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/logging")
public class LoggingResource
{
    private final Logger log = LoggerFactory.getLogger(LoggingResource.class);

    @GetMapping(value = "")
    public ResponseEntity<Void> index(@RequestParam String msg)
    {
        log.info(msg);
        return ResponseEntity.ok().build();
    }
}
