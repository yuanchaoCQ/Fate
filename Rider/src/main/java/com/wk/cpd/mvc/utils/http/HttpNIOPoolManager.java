package com.wk.cpd.mvc.utils.http;

import java.io.IOException;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.DefaultHttpClientIODispatch;
import org.apache.http.impl.nio.pool.BasicNIOConnFactory;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.HttpAsyncRequestExecutor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.nio.reactor.IOReactorException;

import com.wk.cpd.mvc.Constant;

/**
 * http连接池管理类
 *
 */
public class HttpNIOPoolManager {
    private HttpNIOPoolManager() {

    }

    /**
     * 得到{@link HttpNIOPoolManager}的实例
     * 
     * @return 返回{@link HttpNIOPoolManager}的实例
     */
    public static HttpNIOPoolManager getInstance() {
        return instance;
    }

    public static BasicNIOConnPool getPoolInstance() throws IOReactorException {
        return pool;
    }
    private static int AVAIL_PROCS = Runtime.getRuntime().availableProcessors();
    private static HttpNIOPoolManager instance = new HttpNIOPoolManager();

    private static IOReactorConfig getIOReactorConfig() {
        org.apache.http.impl.nio.reactor.IOReactorConfig.Builder builder = IOReactorConfig.custom();
        builder.setSelectInterval(100);
        builder.setShutdownGracePeriod(300);
        builder.setInterestOpQueued(true);
        builder.setIoThreadCount(AVAIL_PROCS);
        builder.setSoTimeout(1000);
        builder.setSoLinger(-1);
        builder.setSoKeepAlive(true);
        builder.setTcpNoDelay(true);
        builder.setConnectTimeout(1000);
        int bufferSize = 4096;
        builder.setSndBufSize(bufferSize);
        builder.setRcvBufSize(bufferSize);
        builder.setBacklogSize(bufferSize);
        return builder.build();
    }

    private volatile static BasicNIOConnPool pool;
    
    

    /**
     * 获取连接池
     * 
     * @return 返回连接池
     * @throws IOReactorException 抛出异常
     */
    public void initPool() throws IOReactorException {
        if (pool == null) {
            synchronized (HttpNIOPoolManager.class) {
                if (pool == null) {
                    final ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(getIOReactorConfig());
                    HttpAsyncRequestExecutor protocolHandler = new HttpAsyncRequestExecutor();
                    ConnectionConfig connectionConfig = ConnectionConfig.custom().build();
                    // Create client-side I/O event dispatch
                    final IOEventDispatch ioEventDispatch = new DefaultHttpClientIODispatch(protocolHandler,
                            connectionConfig);
                    // Create HTTP connection pool
                    pool = new BasicNIOConnPool(ioReactor,
                            new BasicNIOConnFactory(null, null, null, null, null, connectionConfig),
                            (int) Constant.TIMEOUT);
                    // Limit total number of connections to just two
                    pool.setDefaultMaxPerRoute(Constant.MAX_TOTAL);
                    pool.setMaxTotal(Constant.DEFAULT_MAX_PER_ROUTE);
                    // Run the I/O reactor in a separate thread
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                // Ready to go!
                                ioReactor.execute(ioEventDispatch);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }

        }

    }

    public void destroy() throws IOException {
        pool.shutdown(2000);
    }
}
