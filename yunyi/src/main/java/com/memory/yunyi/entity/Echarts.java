package com.memory.yunyi.entity;

/**
 * @Author: 刘博谦
 * @Description:
 * @Date: Created in 15:48 2020/2/18
 * @Modified By:
 */
public class Echarts {
    private String name;
    private Integer num;

    public Echarts(String name, Integer num) {
        super();
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Echarts{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
