package com.cebon.restart.listener;

import com.cebon.restart.properties.ListenerAndStarterProperties;
import com.cebon.restart.utils.CommendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: LiaoPeng
 * @Date: 2019/6/12
 */
@Component
public class StartSchedule {

    @Autowired
    ListenerAndStarterProperties prop;

    String pid;

//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 42 17 * * ?")
    public void closeAndStart(){
        System.out.println("yes");

        String result = CommendUtil.exec(prop.getListenCmd());
        if(result!=null){
            int length = result.length();
            pid = result.substring(length-6,length).trim();
            System.out.println(pid);
        }
        if(result == null){
            System.out.println("kill--------------pid="+pid);
            if(pid!=null){
                CommendUtil.exec("cmd /c taskkill /f /t /PID "+pid);
            }
        }

        String floder = prop.getFloder();
        String cmd = "cmd /c "+floder.substring(0,2)+"&cd "+prop.getFloder()+"&"+prop.getStart();
        CommendUtil.exec(cmd);
        pid = null;
    }

}
