package com.abhijith.SpringJDBCx.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Student
{
    private int roll_no;
    private  String name;
    private double mark;

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = (int) mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    @Override
    public String toString() {
        return "Roll No: " + roll_no + ", Name: " + name + ", Mark: " + mark;
    }
}

