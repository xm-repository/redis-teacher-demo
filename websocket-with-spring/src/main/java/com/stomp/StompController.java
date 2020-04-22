package com.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cj
 * @date 2018/11/19
 */
@Controller
public class StompController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setFrom(message.getFrom());
        outputMessage.setText(message.getText());
        outputMessage.setTime(time);
        return outputMessage;
    }
}
