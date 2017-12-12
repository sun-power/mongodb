package tv.huan.education.entity;

import com.mongodb.DBObject;
import tv.huan.education.utils.JsonUtils;

public class Topic {
    private String topic;
    private String from;
    private String time;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public void parse(DBObject db) {
        this.setTopic((String) db.get("topic"));
        this.setFrom((String) db.get("from"));
        this.setTime((String) db.get("time"));
    }
}
