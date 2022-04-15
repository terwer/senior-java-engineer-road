package com.terwergreen;

/**
 * 学生成绩接口
 *
 * @name: IStuCourse
 * @author: terwer
 * @date: 2022-04-15 11:20
 **/
public interface IStuCourse {
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

    /**
     * 计算总分
     */
    void countTotalScore();

    /**
     * 计算平均分
     */
    void countAverageScore();

    /**
     * 打印成绩信息
     */
    void printStuInfo();

    /**
     * 打印学生信息
     */
    void queryStuInfo();
}
