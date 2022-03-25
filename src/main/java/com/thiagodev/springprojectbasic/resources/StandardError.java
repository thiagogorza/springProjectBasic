package com.thiagodev.springprojectbasic.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private String msg;
    private Long timeStamp;


}
