package com.bjpowernode;

public class Student {

    private Integer id;
    private Integer age;
    private String name;
    private School school;

    public Student(Integer id, Integer age, String name, School school) {
        System.out.println("调用四有参");
        this.id = id;
        this.age = age;
        this.name = name;
        this.school = school;
    }

    public Student(Integer id, String name, School school) {
        System.out.println("调用了这三个参数的有参构造");
        this.id = id;
        this.name = name;
        this.school = school;
    }

    public Student() {
        System.out.println("调用了无参构造");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("setId");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        System.out.println("setSchool");
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}
