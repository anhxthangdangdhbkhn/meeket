package com.meeket.app.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MyFileResponse {
    private long forUserId;
    private Object data;
}
