package com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomErrorDetails {

    private Date timestamp;
    private String message;
    private String errorDetails;
}
