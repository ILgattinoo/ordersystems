package com.alyssa.ordersystems.dao.data;


import com.alyssa.ordersystems.constant.DataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class UserData extends BaseData {
    private String accountId;

    private String token;

    @Enumerated(value = EnumType.STRING)
    private DataType.UserType userType;

    private String nickname = StringUtils.EMPTY;

    @Enumerated(value = EnumType.STRING)
    private DataType.GenderType gender = DataType.GenderType.UNKNOWN;

    private String birthday = StringUtils.EMPTY;

    private String cellphoneNumber = StringUtils.EMPTY;
}
