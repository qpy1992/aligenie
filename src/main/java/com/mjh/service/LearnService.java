package com.mjh.service;

import com.mjh.bean.LearnResouce;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mj.he on 2018-01-18.
 */
@Service
public interface LearnService {


     List<LearnResouce> queryList(int pageIndex, int pageSize) throws RuntimeException;

     int total() throws RuntimeException;

     Map<String,Object> login(Map<String,Object> map);

}
