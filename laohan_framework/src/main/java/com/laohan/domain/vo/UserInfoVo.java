package com.laohan.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;
    private String nickName;
    private String avatar;
    private String sex;
    private String email;

}
