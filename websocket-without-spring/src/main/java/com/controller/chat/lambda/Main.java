package com.controller.chat.lambda;

/**
 * @author cj
 * @date 2018-12-05
 */
public class Main {
    public static void main(String[] args) {
        //lambda

        Person p = new Person();
       /* p.doSth(new SuanFa() {
            @Override
            public int jisuan(int a, int b) {
                return  a + b;
            }
        });*/


        p.doSth((x,y)->  x + y);
    }


}

class Person{

    public void doSth(SuanFa suanFa){
        int result = suanFa.jisuan(5, 6);
        System.out.println(result);
    }
}

interface  SuanFa{
    int jisuan(int a,int b);
}

class SuanFaImpl1 implements  SuanFa{
    @Override
    public int jisuan(int a, int b) {
        return  a + b;
    }
}



class SuanFaImpl2 implements  SuanFa{
    @Override
    public int jisuan(int a, int b) {
        return  a * b;
    }
}
