package com.wk.cpd.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.cpd.mvc.dao.mapper.DspInfoMapper;
import com.wk.cpd.mvc.dao.model.DspInfo;
import com.wk.cpd.mvc.service.TestService;

/**
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private DspInfoMapper dspInfoDao;

    @Override
    @Transactional
    public void insert(int id) throws Exception {
        DspInfo dspInfo = new DspInfo();
        dspInfo.setId(Integer.valueOf(id));
        dspInfo.setIpv4("10.10.15.2");
        dspInfo.setLinkNum(42);
        dspInfo.setPort(522);
        dspInfo.setUrl("sadghdfhadfh");

        dspInfoDao.insert(dspInfo);
        dspInfoDao.insert(dspInfo);
    }

//    @Scheduled(cron="*/5 * * * * ?")
    @Override
    public void scheduledTest() throws Exception {
    }
    
    

}
