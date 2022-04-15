package com.terwergreen;

/**
 * 学分输入接口
 *
 * @name: IStuScoreInput
 * @author: terwer
 * @date: 2022-04-15 11:28
 **/
public interface IStuScoreInput {
    /**
     * 插入成绩
     */
    void insert();

    /**
     * 产出成绩
     */
    void delete();

    /**
     * 修改成绩
     */
    void modify();
}
