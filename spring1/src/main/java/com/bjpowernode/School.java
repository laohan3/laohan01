package com.bjpowernode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("school01")
public class School {
    @Value("英才")
    private String name;
    @Value("常乐集")
    private String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public School() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
