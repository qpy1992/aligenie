package com.mjh.service.impl;

import com.mjh.dao.LearnDao;
import com.mjh.bean.LearnResouce;
import com.mjh.service.LearnService;
import com.mjh.utils.PageParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mj.he on 2018-01-18.
 */
@Service
public class LearnServiceImpl implements LearnService{

    @Autowired
    LearnDao learnDao;
    @Override
    public List<LearnResouce> queryList(int pageIndex, int pageSize) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        PageParse pageParse = new PageParse(pageIndex, pageSize);
        rMap.put(PageParse.STARTNO, pageParse.getStartNo());
        rMap.put(PageParse.ENDNO, pageSize);
        return learnDao.queryList(rMap);
    }

    @Override
    public int total() throws RuntimeException {
        return learnDao.total();
    }

    @Override
    public Map<String, Object> login(Map<String, Object> map) {
        return learnDao.login(map);
    }
}
