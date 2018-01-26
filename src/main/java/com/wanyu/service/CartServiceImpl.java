package com.wanyu.service;

import com.wanyu.mapper.CartMapper;
import com.wanyu.mapper.OrdersMapper;
import com.wanyu.model.Cart;
import com.wanyu.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by samsung on 2017/12/22.
 */
@Service
public class CartServiceImpl {
    @Autowired
    private CartMapper dao;
    @Autowired
    private OrdersMapper odao;
    public int addCart(Map map){
        return dao.addCart(map);
    }
    public List<Cart> queryCart(Map map){
        return dao.queryCart(map);
    }
    public int delCart(Map map){
        return dao.delCart(map);
    }
    public int updateCart(Map map){
        return dao.updateCart(map);
    }
    public int updateByPrimaryKey(Orders record){
        return odao.updateByPrimaryKey(record);
    }
}
