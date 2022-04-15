package com.terwergreen;

/**
 * 成绩汇总
 *
 * @name: IStuScoreCount
 * @author: terwer
 * @date: 2022-04-15 11:30
 **/
public interface IStuScoreCount {
    /**
     * 计算总分
     */
    void countTotalScore();

    /**
     * 计算平均分
     */
    void countAverageScore();
}
