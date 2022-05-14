package com.hansol.first.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response<T> {
    @ApiModelProperty(value = "요청 처리 결과: true or false")
    private Boolean result;

    @ApiModelProperty(value = "요청 처리 코드: 0 성공, -1 오류")
    private Integer resultCode;

    @ApiModelProperty(value = "결과 메세지")
    private String message;

    @ApiModelProperty(value = "반환 데이터: ")
    private T data;
}
