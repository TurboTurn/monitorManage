package com.monitor.MQ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.*;
import com.monitor.service.alarm.AlarmRecordService;
import com.monitor.service.alarm.AlarmService;
import com.monitor.service.alarm.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Component
public class AlarmConsumer {

    private Logger logger=LoggerFactory.getLogger(AlarmConsumer.class);

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlarmRecordService alarmRecordService;

    List<Alarm> alarmList;
    List<UserInfo> userInfoList;

    /***
     * key tank
     * value AlarmObject
     */
    Map<String,Alarm> alarmMap= new HashMap<>();

    /**
     * key username
     * value email
     */
    Map<String,String> emailMap=new HashMap<>();

    /**
     * key username
     * value 待发送的字符串
     */
//    Map<String,ArrayList<String>> userToSend = new HashMap<>();

    /**
     * 这里先写死,后面可以改为从配置文件里面读
     */
    private String email="164497083@qq.com";
    private String subject="数据异常";

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @PostConstruct
    public void init() {
        alarmList = alarmService.selectAll();
        alarmList.forEach(a-> alarmMap.put(a.getTank(),a));

        userInfoList = userService.selectAll();
        userInfoList.forEach(userInfo -> emailMap.put(userInfo.getUsername(),userInfo.getEmail()));
    }


    @KafkaListener(topics = "testTopic")
    public void onMessage(String message){
        List<Tank> list = JSONObject.parseArray(message,Tank.class);//将json转化为Tank数组
//        logger.info(message);

        System.out.println("<----打印队列中的消息------->");
        for (Tank tank:list)
            System.out.println(tank);

        //从mysql数据库去读规则(alarm)
        System.out.println("<-----------打印数据库得到的报警规则------------->");

        alarmList.forEach(alarm -> System.out.println(alarm.toString()));


        //alarmList.forEach(a-> System.out.println(a.toString()));
        //根据读到的数据进行一一对比

        Alarm alarm;
        StringBuilder stringBuilder=new StringBuilder();

//        ArrayList<String> arrayList = new ArrayList<>();
//        ArrayList<String> arrayList1 = new ArrayList<>();

        for (Tank tank:list){
            if(alarmMap.containsKey(tank.getA1_tank())){
                AlarmRecord alarmRecord =new AlarmRecord();
                EmailInfo emailInfo=new EmailInfo();
                boolean Presssure_flag,Height_flag,Height_ld_flag,Height_sf1_flag,Height_sf2_flag,Temperature_flag;

                alarm=alarmMap.get(tank.getA1_tank());

                Presssure_flag=(alarm.getPressure()<tank.getPressure());
                Height_ld_flag=(alarm.getHeight()<tank.getHeight_ld());
                Height_sf1_flag=(alarm.getHeight()<tank.getHeight_sf1());
                Height_sf2_flag=(alarm.getHeight()<tank.getHeight_sf2());

                Height_flag=(Height_ld_flag||Height_sf1_flag||Height_sf2_flag);
                Temperature_flag=(alarm.getTemperature()<tank.getTemperature());

                if(Presssure_flag||Height_flag||Temperature_flag){
                    /**
                     * 设置违反了那一条规则
                     */
                    alarmRecord.setAlarmId((alarm.getId()));
                    /**
                     * 设置警报创建时间
                     */
                    alarmRecord.setHappendTime(new Date());
                    alarmRecord.setTank(tank.getA1_tank());
                    alarmRecord.setOil(tank.getA2_oil());

                    stringBuilder.append(tank.getA1_tank()+" ").append(tank.getA2_oil()+" ");
                    if(Presssure_flag) {
                        /**
                         * alarm表的各个字段记录超标数
                         */
                        alarmRecord.setPressure(tank.getPressure()-alarm.getPressure());
                        stringBuilder.append(tank.getPressure()+" ");
                    }
                    if(Height_ld_flag){
                        alarmRecord.setHeightLd(tank.getHeight_ld()-alarm.getHeight());
                        stringBuilder.append(tank.getHeight_ld()+" ");
                    }
                    if(Height_sf1_flag){
                        alarmRecord.setHeightLd(tank.getHeight_sf1()-alarm.getHeight());
                        stringBuilder.append(tank.getHeight_sf1()+" ");
                    }
                    if(Height_sf2_flag){
                        alarmRecord.setHeightLd(tank.getHeight_sf2()-alarm.getHeight());
                        stringBuilder.append(tank.getHeight_sf2()+" ");
                    }
                    if(Temperature_flag) {
                        stringBuilder.append(tank.getTemperature()+" ");
                    }
                    //stringBuilder.append(" || ");

//                    System.out.println("<----打印alarmRecord的信息----->  "+alarmRecord.toString());
//                    System.out.println("<----打印报警的信息----->  "+stringBuilder.toString());

                    emailInfo.setFrom(email);
                    emailInfo.setTo(emailMap.get(alarm.getUsername()));
                    emailInfo.setSubject(subject);
                    emailInfo.setMsg(stringBuilder.toString());
                    String email = JSON.toJSONString(emailInfo);

                    kafkaTemplate.send("emailTopic",email);

                    /**
                     * 存储到相应的用户名下
                     */
//                    if(userToSend.containsKey(alarm.getUsername())){
//                        arrayList1=userToSend.get(alarm.getUsername());
//                        arrayList1.add(stringBuilder.toString());
//                        userToSend.put(alarm.getUsername(),arrayList1);
//                        //arrayList.clear();
//                    }else {
//                        arrayList1.add(stringBuilder.toString());
//                        userToSend.put(alarm.getUsername(),arrayList1);
//                        //arrayList.clear();
//                    }
                    /**
                     * 打印添加成功后返回的插入数据自动生成的alarmRecord的Id
                     */
                    System.out.println("<----------已经添加的报警记录的Id-------------->");
                    System.out.println(alarmRecordService.InsertAlarmRecord(alarmRecord));
                }
            }
        }

        /**
         * 如果对应topic里面的数据一次太多，并且需要将一个人对应的报警信息包装为一整条，就采用这个类里面已经注释过的方法
         */
//        if(!userToSend.isEmpty()){
//            System.out.println("<---------------异常数据打印----------->");
//
//             for(Map.Entry<String, ArrayList<String>> entry : userToSend.entrySet()) {
//                 System.out.println("键 key ：" + entry.getKey() + " 值value ：" + entry.getValue());
//                 String str=String.join(" ",entry.getValue());
//                 System.out.println(str);
////               sendAlarmMail(str,emailMap.get(entry.getKey()));
//             }
//             arrayList1.clear();
//             userToSend.clear();
//        }
    }


}
