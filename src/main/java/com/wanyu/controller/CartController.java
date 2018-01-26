package com.wanyu.controller;

import com.wanyu.model.Cart;
import com.wanyu.model.Orders;
import com.wanyu.service.CartServiceImpl;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samsung on 2017/12/22.
 */
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl service;
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResponseEntity<String> addCart(@RequestBody Cart cart){
        Map map=new HashMap<>();
        map.put("in_username",cart.getUsername());
        map.put("in_itemid",cart.getItemid());
        map.put("in_quantity",cart.getQuantity());
        service.addCart(map);
        String oid=map.get("out_oid").toString();//订单编号
        System.out.println("ok");
        return new ResponseEntity<String>(oid,HttpStatus.OK);
    }
    @RequestMapping(value = "/query/{username}/{oid}",method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> queryCart(@PathVariable String username,@PathVariable String oid){
        Map map=new HashMap<>();
        map.put("in_username",username);
        map.put("in_oid",oid);
        List<Cart> list=service.queryCart(map);
        return new ResponseEntity<List<Cart>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public ResponseEntity<String> delCart(@RequestBody Cart cart){
        Map map=new HashMap<>();
        map.put("in_itemid",cart.getItemid());
        map.put("in_username",cart.getUsername());
        map.put("in_orderid",cart.getOrderid());
        service.delCart(map);
        System.out.println(cart.getOrderid());
        return new ResponseEntity(cart.getOrderid(),HttpStatus.OK);
    }
    @RequestMapping(value = "/up",method=RequestMethod.PUT)
    public ResponseEntity<String> upCart(@RequestBody Cart cart){
        Map map=new HashMap<>();
        map.put("in_username",cart.getUsername());
        map.put("in_itemid",cart.getItemid());
        map.put("in_quantity",cart.getQuantity());
        map.put("in_orderid",cart.getOrderid());
        service.updateCart(map);
        System.out.println("wo");
        return new ResponseEntity(cart.getOrderid(),HttpStatus.OK);
    }
    @RequestMapping(value = "/commit",method = RequestMethod.PUT)
    public ResponseEntity<Void> comOrders(@RequestBody Orders orders){
        service.updateByPrimaryKey(orders);
        System.out.println("OOKK");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
