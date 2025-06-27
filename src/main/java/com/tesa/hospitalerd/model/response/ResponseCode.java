package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("00", "Success"),
    ERROR  ("22", "Error");

    private final String code;
    private final String defaultMessage;


}
