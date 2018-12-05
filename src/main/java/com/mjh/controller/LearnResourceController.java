package com.mjh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mjh.dao.LearnDao;
import com.mjh.bean.LearnResouce;
import com.mjh.service.LearnService;
import com.mjh.utils.MD5Util;
import com.mjh.utils.PageParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询
 */
@Controller
@RequestMapping("/learn")
public class LearnResourceController {
    @Autowired
    LearnService learnService;

    @RequestMapping("index")
    public String index() {
        return "login/index";
    }

    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map<String, Object> query(String message){

        JSONObject jsonObject = JSON.parseObject(message);
        int pageIndex = jsonObject.getIntValue("pageIndex");
        int pageSize = jsonObject.getIntValue("pageSize");
        Map<String, Object> rMap = new HashMap<String, Object>();
        List<LearnResouce> learnList = learnService.queryList(pageIndex, pageSize);
        int total = learnService.total();

        rMap.put("total", total);
        rMap.put("rows", learnList);
        rMap.put("pages", PageParse.getPages(pageSize, total));
        return rMap;
    }

    @ResponseBody
    @RequestMapping(value="print",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ModelAndView print(HttpServletRequest request){
        request.setAttribute("table",request.getParameter("table"));
        int x=(int)(Math.random()*100);
        String truth = x+"="+x;
        request.setAttribute("true",truth);
        return new ModelAndView("paiche/report");
    }

    @ResponseBody
    @RequestMapping(value="json",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public ModelAndView json(){
        return new ModelAndView("paiche/data/jsonData");
    }

    @ResponseBody
    @RequestMapping(value="login",method=RequestMethod.GET,produces="application/json;charset=utf-8")
    public ModelAndView login(HttpServletRequest request){
        return new ModelAndView("login/login");
    }

    @ResponseBody
    @RequestMapping(value="submit",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    public JSONObject submit(HttpServletRequest request){
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,Object> map = new HashMap<>();
        JSONObject json = new JSONObject();
        map.put("username",name);
        map.put("password", MD5Util.getMd5(password));
        Map<String,Object> info = learnService.login(map);
        if(info==null){
            json.put("message","手机或者密码错误");
            json.put("code",0);
        }else{
            String fstatus = info.get("fstatus").toString();

            if(fstatus.equals("1"))
            {
                json.put("message","用户禁用，无法登陆");
                json.put("code",2);
                return json;
            }
            json.put("message","用户登录成功");
            json.put("memberInfo",info);
            json.put("code",1);
        }
        return json;
    }
}
