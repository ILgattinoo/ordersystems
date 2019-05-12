package com.alyssa.ordersystems.controller;

import com.alyssa.ordersystems.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping (value = "/registered",method = RequestMethod.GET)
    public Map<String, Object> registered(
            @RequestParam(value = "accountId") String accountId,
            @RequestParam(value = "token") String token,
            @RequestParam(value = "userType") int userType) {
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.registered(accountId, token, userType));
        return model;
    }

    @RequestMapping(value = "/unsubscribe",method = RequestMethod.GET)
    public Map<String, Object> unsubscribe(@RequestParam(value = "id") long id) {

        service.unsubscribe(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public Map<String, Object> modify(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "password", required = false, defaultValue = "") String password,
            @RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
            @RequestParam(value = "gender", required = false, defaultValue = "-1") int gender,
            @RequestParam(value = "birthday", required = false, defaultValue = "") String birthday,
            @RequestParam(value = "cellphoneNumber", required = false, defaultValue = "") String cellphoneNumber) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        model.put("user", service.update(id, password, nickname,
                gender, birthday, cellphoneNumber));
        return model;
    }

    @RequestMapping(value = "/getByAccountId",method = RequestMethod.GET)
    public Map<String, Object> getByAccountId(
            @RequestParam(value = "accountId") String accountId) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("userinfo", service.getByAccountId(accountId));
        return model;
    }

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("userList", service.listAll(pageNumber, pageSize));
        return model;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "accountId") String accountId,
            @RequestParam(value = "password" ) String password) {
        return service.login(accountId,password);
    }
}
