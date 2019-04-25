package com.alyssa.ordersystems.dao.manager;

import com.alyssa.ordersystems.dao.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserManager extends JpaRepository<UserData,Long> {
    Optional<UserData> getByAccountId(String accountId);

}
