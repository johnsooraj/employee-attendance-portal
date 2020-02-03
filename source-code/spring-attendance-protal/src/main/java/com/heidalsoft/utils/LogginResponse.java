package com.heidalsoft.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogginResponse {

    private Boolean loggingStatus;
    private String message;

}
