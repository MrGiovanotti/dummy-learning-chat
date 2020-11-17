package com.dummy.learning.chat.services;

import java.util.List;
import com.dummy.learning.chat.documents.Message;

public interface MessageService {

  List<Message> getLast10();

  Message save(Message message);

}
