package com.becypress.boc.pay.service;

import com.becypress.boc.pay.config.BocPayProperties;
import com.becypress.boc.pay.domain.*;
import com.becypress.toolkit.Becypress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

/**
 * Description: BocPayServ.<br>
 * Date: 2017/10/31 18:51<br>
 * Author: ysj
 */
public interface BocPayServ
{
    Logger log = LoggerFactory.getLogger(BocPayServ.class);

    BocPayProperties getBocPayProperties();

    BocPayConfig getBocPayConfigStorage();

    default String response(String result)
    {
        log.info("Response: {}", result);
        return result;
    }

    default <Req extends BocPayReqData> String request(Req reqData) throws Exception
    {
        return this.request(reqData, HttpMethod.POST);
    }

    default <Req extends BocPayReqData> String request(Req reqData, HttpMethod method) throws Exception
    {
        String req = reqData.toXml();
        String api = getBocPayProperties().getGateway() + reqData.getApi();
        log.info("Request: {}, {}", api, req);
        switch (method)
        {
            case GET:
                return this.response(Becypress.UTIL.extra().http().doGet(api));
            case POST:
                return this.response(Becypress.UTIL.extra().http().doPostSSLWithXML(api, req));
        }
        return null;
    }

    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiUnifiedorder(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new UnifiedorderResData().fromXml(resXml);
    }

    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiPrecreate(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new PrecreateResData().fromXml(resXml);
    }

    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiOrderquery(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new OrderqueryResData().fromXml(resXml);
    }

    /**
     * 微信冲正
     *
     * @param reqData
     * @param <Req>
     * @param <Res>
     * @return
     * @throws Exception
     */
    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiOrderReverse(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new OrderReverseResData().fromXml(resXml);
    }

    /**
     * 微信退款进度查询
     *
     * @param reqData
     * @param <Req>
     * @param <Res>
     * @return
     * @throws Exception
     */
    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiRefundQuery(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new RefundqueryResData().fromXml(resXml);
    }

    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiGatewayCreate(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new PayGatewayCreateResData().fromXml(resXml);
    }

    default <Req extends BocPayReqData, Res extends BocPayResData> Res apiGatewayQuery(Req reqData) throws Exception
    {
        reqData.setAppid(getBocPayConfigStorage().getAppId());
        reqData.setMchId(getBocPayConfigStorage().getMchId());
        String resXml = this.request(reqData.sign(getBocPayConfigStorage().getKey()));
        return new PayGatewayQueryResData().fromXml(resXml);
    }
}
