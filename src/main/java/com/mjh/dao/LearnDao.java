package com.mjh.dao;

import com.mjh.bean.LearnResouce;

import java.util.List;
import java.util.Map;

/**
 * Created by mj.he on 2018-01-18.
 */
public interface LearnDao {
    List<LearnResouce> queryList(Map<String, Object> conditions);
    int total();
    Map<String,Object> login(Map<String,Object> map);
}
