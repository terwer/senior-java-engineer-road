package com.terwergreen.config;

import com.terwergreen.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @name: BoundSql
 * @author: terwer
 * @date: 2022-03-14 17:13
 **/
public class BoundSql {
    /**
     * 解析后的sql
     */
    private String sqlText;
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
