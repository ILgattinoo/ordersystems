package com.alyssa.ordersystems.service;

import com.alyssa.ordersystems.constant.DataType;
import com.alyssa.ordersystems.dao.data.UserData;
import com.alyssa.ordersystems.dao.manager.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.alyssa.ordersystems.constant.DataType.UserType.CUSTOMER;


@Slf4j
@Service
public class UserService {
    @Autowired
    private UserManager manager;

    @Transactional
    public boolean registered(String accountId, String token, int userType) {
        if (manager.getByAccountId(accountId).isPresent()) {
            return false;
        }
        create(accountId, token, DataType.UserType.getType(userType).orElse(CUSTOMER));
        return true;
    }

    private void create(String accountId, String token, DataType.UserType userType) {
        manager.save(new UserData()
                .setAccountId(accountId)
                .setToken(token)
                .setUserType(userType));
    }

    @Transactional
    public UserData update(long id, String token, String nickname,
                           int gender, String birthday, String cellphoneNumber)
            throws Exception {
        Optional<UserData> data = manager.findById(id);
        if (!data.isPresent()) {
            throw new Exception("The user does not exist. id=" + id);
        }
        if (!StringUtils.isBlank(token)) {
            data.get().setToken(token);
        }
        if (!StringUtils.isBlank(nickname)) {
            data.get().setNickname(nickname);
        }

        DataType.GenderType.getType(gender).ifPresent(
                genderType -> data.get().setGender(genderType));

        if (!StringUtils.isBlank(cellphoneNumber)) {
            data.get().setCellphoneNumber(cellphoneNumber);
        }
        if (!StringUtils.isBlank(birthday)) {
            data.get().setBirthday(birthday);
        }
        return manager.save(data.get());
    }

    public void unsubscribe(long id) {
        manager.findById(id).ifPresent((o) -> manager.delete(o));
    }

    public UserData getByAccountId(String accountId) {
        return manager.getByAccountId(accountId).orElse(null);
    }

    public List<UserData> listAll(int pageNumber, int pageSize) {
        return manager.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();
    }

}
