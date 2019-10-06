package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao = new RouteDaoImpl();
    @Override
    public PageBean<Route> findPage(int cid, int currentPage, int pageSize) {
        int totalCount = routeDao.findCount(cid);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize  + 1;
        int start = (currentPage - 1) * pageSize;
        List<Route> routes = routeDao.findPage(cid, start, pageSize);
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(routes);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }
}
