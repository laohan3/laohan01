package com.example.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Operate {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Province2 province2=new Province2();
    List<Province2> list=new ArrayList<>();
    String sql="select id,name from province2";

    public List<Province2> ProvinceQuery(){
        try {
            conn=FengZhuang.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                province2=new Province2(id,name);
                list.add(province2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            FengZhuang.close(conn,ps,rs);
        }
        return list;
    }

    public List<City> CityQuery(Integer index){
        List<City> list2=new ArrayList<>();
        String sql="select id,name,provinceid from city where provinceid=?";
        try {
            conn=FengZhuang.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,index);
            rs=ps.executeQuery();
            while (rs.next()){
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                Integer provinceid=rs.getInt("provinceid");
                City city=new City(id,name,provinceid);
                list2.add(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            FengZhuang.close(conn,ps,rs);
        }
        return list2;
    }


}
