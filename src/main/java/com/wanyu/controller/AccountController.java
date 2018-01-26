package com.wanyu.controller;

import com.wanyu.model.Account;
import com.wanyu.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by samsung on 2017/11/30.
 */
@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountServiceImpl service;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<Account> login(@RequestBody Account account, HttpServletResponse response){
        if(service.login(account.getUsername(),account.getPassword())!=null){
//            Cookie cookie=new Cookie("username",account.getUsername());
//            cookie.setMaxAge(3600*24*7);
//            response.addCookie(cookie); 在前端进行cookie处理
            return new  ResponseEntity<Account>(account,HttpStatus.OK);
        }else {
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value="/reg",method = RequestMethod.POST)
    public ResponseEntity<Void> reg(@RequestBody Account account){

        if(service.selectByUsername(account).size()>0){//已经有注册的
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        if(service.reg(account)>0){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        throw new RuntimeException();
    }
}
