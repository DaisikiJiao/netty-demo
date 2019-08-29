package com.lxx.nettyclientdemo.test;

/**
 * Created by luox on 2018/8/17.
 */
public class Test {

    private Test1 t1;
    private Test2 t2;

    public void main(){

        t1.setTest2(t2);
        t2.setTest1(t1);

    }

}
