package com.wanyu.controller;

import com.wanyu.model.Category;
import com.wanyu.model.Item;
import com.wanyu.model.Product;
import com.wanyu.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by samsung on 2017/11/30.
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetServiceImpl service;
    @RequestMapping(value = "/queryc",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> queryPet(){
        List<Category> list=service.selectCategory();
        if(list.size()>0){
            System.out.println(1);
            return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Category>>(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryp/{catid}",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> queryProduct(@PathVariable String catid){
        List<Product> list=service.selectProduct(catid);
        if(list.size()>0){
            return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Product>>(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryi/{productid}",method=RequestMethod.GET)
    public ResponseEntity<List<Item>> queryItems(@PathVariable String productid){
        List<Item> list=service.selectItems(productid);
        if(list.size()>0){
            return new ResponseEntity<List<Item>>(list,HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Item>>(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/queryit/{itemid}",method =RequestMethod.GET)
    public ResponseEntity<Item> queryItem(@PathVariable String itemid){
        Item item=service.selectItem(itemid);
        if(item!=null){
            return new ResponseEntity<Item>(item,HttpStatus.OK);
        }else {
            return new ResponseEntity<Item>(HttpStatus.CONFLICT);
        }
    }
}
