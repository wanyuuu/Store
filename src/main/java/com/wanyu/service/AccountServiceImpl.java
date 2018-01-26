package com.wanyu.service;

import com.wanyu.mapper.AccountMapper;
import com.wanyu.mapper.ProfileMapper;
import com.wanyu.model.Account;
import com.wanyu.model.AccountExample;
import com.wanyu.model.Profile;
import com.wanyu.model.ProfileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by samsung on 2017/11/30.
 */
@Service
public class AccountServiceImpl {
    @Autowired
    private AccountMapper dao;
    @Autowired
    private ProfileMapper pdao;
    public Account login(String username,String password ){//登录
        return dao.login(username,password);
    }
    public List<Account> selectByUsername(Account account){ //检查用户名是否重复
        AccountExample acc=new AccountExample();
        acc.createCriteria().andUsernameEqualTo(account.getUsername());//组合条件
        List<Account> list=dao.selectByExample(acc);
        return list;
    }
    public int reg(Account account){//注册

        dao.insert(account);// 先主表
        Profile profile=account.getProfile();
        profile.setUsername(account.getUsername());
        return pdao.insert(profile); //从表
    }
}
