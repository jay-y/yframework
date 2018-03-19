package com.cust.biz.service;

import com.becypress.alipay.service.AlipayServ;
import com.becypress.alipay.service.AlipayServs;
import com.becypress.boc.pay.service.BocPayServ;
import com.becypress.boc.pay.service.BocPayServs;
import com.becypress.ftp.service.FTPClient;
import com.becypress.ftp.service.FTPClients;
import com.becypress.wechat.service.WechatServ;
import com.becypress.wechat.service.WechatServs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: SLEDUServ.<br>
 * Date: 2017/10/31 19:19<br>
 * Author: ysj
 */
@Transactional(readOnly = true)
@Service
public class CustServ
{
    private static final String _SERV_ID = "test";
    private final AlipayServ alipayServ;
    private final WechatServ wechatServ;
    private final BocPayServ bocPayServ;
    private final FTPClient ftpClient;

    public CustServ(AlipayServs alipayServs, WechatServs wechatServs, BocPayServs bocPayServs, FTPClients ftpClients)
    {
        this.alipayServ = alipayServs.getServ(_SERV_ID);
        this.wechatServ = wechatServs.getServ(_SERV_ID);
        this.bocPayServ = bocPayServs.getServ(_SERV_ID);
        this.ftpClient = ftpClients.getClient(_SERV_ID);
    }

    public AlipayServ getAlipayServ()
    {
        return alipayServ;
    }

    public WechatServ getWechatServ()
    {
        return wechatServ;
    }

    public BocPayServ getBocPayServ()
    {
        return bocPayServ;
    }

    public FTPClient getFtpClient()
    {
        return ftpClient;
    }
}
