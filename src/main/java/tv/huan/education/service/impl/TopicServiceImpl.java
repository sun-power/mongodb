package tv.huan.education.service.impl;


import com.mongodb.*;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tv.huan.education.entity.Topic;
import tv.huan.education.service.TopicService;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    private static final Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override

    public DBObject save(Topic topic) throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        DBObject dbo = (DBObject) JSON.parse(topic.toJson());
        userCollection.insert(dbo);
        log.info("保存的信息：save ======" + dbo);
        return dbo;
    }

    public DBObject update(Topic topic) throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        BasicDBObject baseDBO = new BasicDBObject();
        baseDBO.put("topic", topic.getTopic());
        DBObject newDBO = (DBObject) JSON.parse(topic.toJson());
        userCollection.update(baseDBO, newDBO);
        log.info("修改的信息：update ======" + newDBO);
        return null;
    }

    public List<Topic> delete(String topic) throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        BasicDBObject queryObject = new BasicDBObject("topic", topic);
        userCollection.remove(queryObject);
        List<Topic> userList = new ArrayList<Topic>();
        DBCursor cursor = userCollection.find();
        while (cursor.hasNext()) {
            Topic topic1 = new Topic();
            topic1.parse(cursor.next());
            userList.add(topic1);
        }
        log.info("删除剩下的信息：delete ======" + userList);
        return userList;
    }

    public List<Topic> deleteAll() throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        userCollection.drop();
        DBCursor cursor = userCollection.find();
        List<Topic> userList = new ArrayList<Topic>();
        while (cursor.hasNext()) {
            Topic topic = new Topic();
            topic.parse(cursor.next());
            userList.add(topic);
        }
        log.info("删除所有信息剩下的信息：findAll ======" + userList);
        return userList;
    }


    public List<Topic> select(String topic) throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        BasicDBObject queryObject = new BasicDBObject("topic", topic);
        DBCursor cursor = userCollection.find(queryObject);
        List<Topic> userList = new ArrayList<Topic>();
        while (cursor.hasNext()) {
            Topic topic1 = new Topic();
            topic1.parse(cursor.next());
            userList.add(topic1);
        }
        log.info("查询的信息：select ======" + userList);
        return userList;
    }

    public List<Topic> findAll() throws UnknownHostException {
        Mongo mongo = new Mongo();
        DB myMongo = mongo.getDB("mongo_Test");
        DBCollection userCollection = myMongo.getCollection("topic");
        DBCursor cursor = userCollection.find();
        List<Topic> userList = new ArrayList<Topic>();
        while (cursor.hasNext()) {
            Topic topic = new Topic();
            topic.parse(cursor.next());
            userList.add(topic);
        }
        log.info("所有的信息：findAll ======" + userList);
        return userList;
    }

}
