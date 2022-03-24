package com.bjpowernode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testStudent(){
        String config="beans.xml";
        //创建容器,<beans>中所有对象被创建
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        Student student=(Student)ac.getBean("student0");
        System.out.println(student);

    }
    @Test
    public void testStudent2(){
        String config="beans.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);
        //创建一个Date类;
        Date date=(Date)ac.getBean("myDate");
        System.out.println(date);

    }
    @Test
    public void testStudent3() throws IOException {
        String config="beans.xml";
        ApplicationContext ac= new ClassPathXmlApplicationContext(config);



//        String inPath="C:/Users/88430/Desktop/notes/mybatis.txt";
//        String outPath="C:/Users/88430/Desktop/notes/copy.txt";
//        FileInputStream fis=new FileInputStream(inPath);
//        FileOutputStream fos=new FileOutputStream(outPath);
//        byte[]bytes=new byte[1020];
//        int num=0;
//        while ((num=fis.read(bytes))!=-1){
//            fos.write(bytes,0,num);

    }
}
