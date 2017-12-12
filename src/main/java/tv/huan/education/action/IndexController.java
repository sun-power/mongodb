package tv.huan.education.action;


import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tv.huan.education.entity.Topic;
import tv.huan.education.service.TopicService;

import java.net.UnknownHostException;
import java.util.List;

@Controller
@Scope("prototype")
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TopicService service;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/save")
    @ResponseBody
    public DBObject update(String topic, String from, String time) throws UnknownHostException {
        Topic topic1 = new Topic();
        topic1.setTopic(topic);
        topic1.setTime(time);
        DBObject saveInfo;
        List<Topic> list = service.select(topic);
        if (list == null || list.size() == 0) {
            topic1.setFrom(from);
            saveInfo = service.save(topic1);
        } else {
            JSONObject jo = new JSONObject();
            jo.put("list", list);
            String str = jo.toString().split(":")[2].split(",")[0];
            System.out.print(str);
            topic1.setFrom(str.substring(1, str.length() - 1) + "-" + from);
            saveInfo = service.update(topic1);
        }
        return saveInfo;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(String topic) throws UnknownHostException {
        List<Topic> ls = service.delete(topic);
        JSONObject lsObj = new JSONObject();
        lsObj.put("user", ls);
        System.out.print(ls);
        return lsObj;
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public JSONObject deleteAll() throws UnknownHostException {
        List<Topic> lsAll = service.deleteAll();
        JSONObject lsAllObj = new JSONObject();
        lsAllObj.put("user", lsAll);
        System.out.print(lsAll);
        return lsAllObj;
    }

    @RequestMapping("/select")
    @ResponseBody
    public JSONObject select(String topic) throws UnknownHostException {
        List<Topic> user = service.select(topic);
        JSONObject obj = new JSONObject();
        obj.put("user", user);
        System.out.print(obj);
        return obj;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public JSONObject findAll() throws UnknownHostException {
        List<Topic> data = service.findAll();
        JSONObject jo = new JSONObject();
        jo.put("data", data);
        return jo;
    }

}
