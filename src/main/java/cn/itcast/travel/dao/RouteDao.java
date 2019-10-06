package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int findCount(int cid);
    List<Route> findPage(int cid,int start,int pageSize);
}
