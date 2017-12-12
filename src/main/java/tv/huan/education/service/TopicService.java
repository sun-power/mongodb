package tv.huan.education.service;

import com.mongodb.DBObject;
import tv.huan.education.entity.Topic;

import java.net.UnknownHostException;
import java.util.List;

public interface TopicService {

    DBObject save(Topic topic) throws UnknownHostException;

    DBObject update(Topic topic) throws UnknownHostException;

    List<Topic> delete(String topic) throws UnknownHostException;

    List<Topic> deleteAll() throws UnknownHostException;

    List<Topic> select(String topic) throws UnknownHostException;

    List<Topic> findAll() throws UnknownHostException;

}
