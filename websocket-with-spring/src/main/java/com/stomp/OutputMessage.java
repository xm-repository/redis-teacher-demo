package com.stomp;

import lombok.Data;

/**
 * @author cj
 * @date 2018/11/19
 */
@Data
public class OutputMessage {
    private  String from;
    private String text;
    private String time;
}
