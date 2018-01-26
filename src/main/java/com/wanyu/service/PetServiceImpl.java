package com.wanyu.service;

import com.wanyu.mapper.CategoryMapper;
import com.wanyu.mapper.ItemMapper;
import com.wanyu.mapper.ProductMapper;
import com.wanyu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by samsung on 2017/11/30.
 */
@Service
public class PetServiceImpl {
    @Autowired
    CategoryMapper mapper;
    @Autowired
    ProductMapper pdao;
    @Autowired
    ItemMapper idao;
    public List<Category> selectCategory(){ //注册时查询出的动物种类
        CategoryExample exmaple=new CategoryExample();
        exmaple.createCriteria().andCatidIsNotNull();
        return mapper.selectByExample(exmaple);
    }
    public List<Product> selectProduct(String catid){//产品种类
        ProductExample example=new ProductExample();
        example.createCriteria().andCatidEqualTo(catid);
        return pdao.selectByExample(example);
    }
    public List<Item> selectItems(String productid){//根据产品编号查出项目
        ItemExample example=new ItemExample();
        example.createCriteria().andProductidEqualTo(productid);
        return idao.selectByExample(example);
    }
    public Item selectItem(String itemid){//根据项目编号查具体信息
        return idao.selectByPrimaryKey(itemid);
    }
}
