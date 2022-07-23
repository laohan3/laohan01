package com.example.servlet;

import com.example.controller.Operate;
import com.example.controller.Province2;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvince2Servlet extends HttpServlet {//ajax-servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Operate operate=new Operate();
        List<Province2> provinces=operate.ProvinceQuery();
        String json="{}";
        //调用jackson工具库,将结果集合转化为json格式字符串,响应ajax的请求;
        if(provinces!=null){
            ObjectMapper om=new ObjectMapper();
            json=om.writeValueAsString(provinces);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(json);

    }


}
