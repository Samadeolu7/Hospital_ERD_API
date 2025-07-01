package com.tesa.hospitalerd.util;


import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.ResponseCode;

public class ResponseBuilder {
    public static <AnyDataType> ApiResponse<AnyDataType> success(AnyDataType data) {
        return new ApiResponse<>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getDefaultMessage(),
                data
        );
    }

    public static <AnyDataType> ApiResponse<AnyDataType> success(String customMessage, AnyDataType data) {
        return new ApiResponse<>(
                ResponseCode.SUCCESS.getCode(),
                customMessage,
                data
        );
    }

    public static <AnyDataType> ApiResponse<AnyDataType> error(String message) {
        return new ApiResponse<>(
                ResponseCode.ERROR.getCode(),
                message,
                null
        );
    }
}
