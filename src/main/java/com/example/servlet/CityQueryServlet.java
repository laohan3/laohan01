package com.example.servlet;

import com.example.controller.City;
import com.example.controller.Operate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json="{}";
        String provinceid0=request.getParameter("provinceid");
        Integer provinceid=Integer.valueOf(provinceid0);
        Operate operate=new Operate();
        List<City> cityList=operate.CityQuery(provinceid);
        //转换为json格式
        ObjectMapper om=new ObjectMapper();
        json=om.writeValueAsString(cityList);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(json);


    }


}
