package com.terwergreen;

/**
 * 课程接口
 *
 * @name: ICourse
 * @author: terwer
 * @date: 2022-04-14 19:46
 **/
public interface ICourse {
    /**
     * ID
     *
     * @return
     */
    Integer getId();

    /**
     * 课程名称
     *
     * @return
     */
    String getName();

    /**
     * 课程价格
     *
     * @return
     */
    Float getPrice();
}
