package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize;
        if(pageSizeStr==null || "".equals(pageSizeStr)){
            pageSize = 5;
        }else{
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int cid;
        if(cidStr==null || "".equals(cidStr)){
            cid = 1;
        }else{
            cid = Integer.parseInt(cidStr);
        }
        int currentPage;
        if(currentPageStr==null || "".equals(currentPageStr)){
            currentPage = 1;
        }else{
            currentPage = Integer.parseInt(currentPageStr);
        }
        RouteService service = new RouteServiceImpl();
        PageBean<Route> pb = service.findPage(cid, currentPage, pageSize);
        writeValue(pb,response);

    }


}
