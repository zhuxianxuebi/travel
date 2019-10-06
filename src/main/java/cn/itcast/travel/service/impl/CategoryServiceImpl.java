package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    Jedis jedis = JedisUtil.getJedis();
    @Override
    public List<Category> findAll() {
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);
        List<Category> category_list = null;
        if (categories!=null&&categories.size()>0){
            category_list = new ArrayList<>();
            for (Tuple tuple:categories) {
                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());
                category_list.add(category);
            }
            System.out.println("使用redis查找");
        }else{
            category_list = categoryDao.findAll();
            System.out.println("使用数据库查找");
            for(Category cg:category_list){
                jedis.zadd("category",cg.getCid(),cg.getCname());
            }
        }
        return category_list;
    }
}
