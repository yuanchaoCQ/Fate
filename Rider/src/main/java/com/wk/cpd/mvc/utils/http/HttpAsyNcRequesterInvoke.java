package com.wk.cpd.mvc.utils.http;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.protocol.HttpCoreContext;

import com.wk.cpd.mvc.vo.oppo.OppoInterfaceType;

/**
 * @description: 多路复用发送基础类
 */
public interface HttpAsyNcRequesterInvoke {

    public void invoke(BasicNIOConnPool pool, final HttpHost target, final HttpCoreContext coreContext,
            final CountDownLatch latch, final Map<OppoInterfaceType, Object> resultMap,
            Class<? extends Object> responseClass);
}
