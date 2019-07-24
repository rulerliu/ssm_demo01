package com.liuwq.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User extends PageBO {
    private Integer id;

    private String name;

    private Integer age;

}