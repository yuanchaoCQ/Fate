package com.wk.cpd.mvc.vo.oppo;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.protocol.HttpCoreContext;

import com.wk.cpd.mvc.utils.http.HttpAsyNcRequesterInvoke;

/**
 * @description: 
 */
public class OppoClient implements HttpAsyNcRequesterInvoke {

    @Override
    public void invoke(BasicNIOConnPool pool, HttpHost target, HttpCoreContext coreContext, CountDownLatch latch,
            Map<OppoInterfaceType, Object> resultMap, Class<? extends Object> responseClass) {
    }

}
