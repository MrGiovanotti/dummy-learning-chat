package com.dummy.learning.chat.controllers;

import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.dummy.learning.chat.documents.Message;
import com.dummy.learning.chat.services.MessageService;

@Controller
public class ChatController {

  private String[] colors = {"red", "green", "blue", "magenta", "purple", "orange"};

  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate simpMessageTemplate;

  @MessageMapping("/message")
  @SendTo("/chat/message")
  public Message receiveMessage(Message message) {
    message.setDate(new Date().getTime());
    if ("NEW_USER".equalsIgnoreCase(message.getType())) {
      message.setColor(colors[new Random().nextInt(colors.length)]);
      message.setText("Nuevo Usuario");
    } else {
      messageService.save(message);
    }
    return message;
  }

  @MessageMapping("/escribiendo")
  @SendTo("/chat/escribiendo")
  public String notificateWriting(String username) {
    return username.concat(" está escribiendo...");
  }

  @MessageMapping("/history")
  public void getMessagesHistory(String clientId) {
    simpMessageTemplate.convertAndSend("/chat/history/" + clientId, messageService.getLast10());
  }

}
