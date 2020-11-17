package com.dummy.learning.chat.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.dummy.learning.chat.documents.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

  List<Message> findFirst10ByOrderByDateDesc();

}
