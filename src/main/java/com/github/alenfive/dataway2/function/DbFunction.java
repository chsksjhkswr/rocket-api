package com.github.alenfive.dataway2.function;

import com.github.alenfive.dataway2.datasource.DataSourceManager;
import com.github.alenfive.dataway2.extend.ApiInfoContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description:脚本运行
 * @Copyright: Copyright (c) 2019  ALL RIGHTS RESERVED.
 * @Author: 米华军
 * @CreateDate: 2020/6/23 17:58
 * @UpdateDate: 2020/6/23 17:58
 * @UpdateRemark: init
 * @Version: 1.0
 */
@Component
@Slf4j
public class DbFunction implements IFunction{

    @Autowired
    private DataSourceManager dataSourceManager;

    @Autowired
    private ApiInfoContent apiInfoContent;

    @Override
    public String getVarName() {
        return "db";
    }

    public Long count(String script,String dataSource){
        Map<String,Object> value = findOne(script,dataSource);
       if (value == null)return null;
       if(value.values().size() == 0)return null;
       return Long.valueOf(value.values().toArray()[0].toString());
    }

    public Map<String,Object> findOne(String script,String dataSource){
        List<Map<String,Object>> list = find(script,dataSource);
        if (list.size() == 0)return null;
        return list.get(0);
    }

    public List<Map<String,Object>> find(String script,String dataSource){
        StringBuilder sbScript = new StringBuilder(script);
        List<Map<String,Object>> result = dataSourceManager.find(sbScript,apiInfoContent.getApiInfo(),apiInfoContent.getApiParams(),dataSource);
        log.info("generate script:{}",sbScript);
        return result;
    }

    public Object insert(String script,String dataSource){
        StringBuilder sbScript = new StringBuilder(script);
        Object result = dataSourceManager.insert(sbScript,apiInfoContent.getApiInfo(),apiInfoContent.getApiParams(),dataSource);
        log.info("generate script:{}",sbScript);
        return result;
    }

    public Object remove(String script,String dataSource){
        StringBuilder sbScript = new StringBuilder(script);
        Object result =  dataSourceManager.remove(sbScript,apiInfoContent.getApiInfo(),apiInfoContent.getApiParams(),dataSource);
        log.info("generate script:{}",sbScript);
        return result;
    }

    public Long update(String script,String dataSource){
        StringBuilder sbScript = new StringBuilder(script);
        Long result =  dataSourceManager.update(sbScript,apiInfoContent.getApiInfo(),apiInfoContent.getApiParams(),dataSource);
        log.info("generate script:{}",sbScript);
        return result;
    }

}