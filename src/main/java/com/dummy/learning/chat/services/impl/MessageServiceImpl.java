package com.dummy.learning.chat.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dummy.learning.chat.documents.Message;
import com.dummy.learning.chat.repositories.MessageRepository;
import com.dummy.learning.chat.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageRepository messageRepository;

  @Override
  public List<Message> getLast10() {
    return messageRepository.findFirst10ByOrderByDateDesc();
  }

  @Override
  public Message save(Message message) {
    return messageRepository.save(message);
  }

}
