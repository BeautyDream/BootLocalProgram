package com.cebon.restart.listener;

import com.cebon.restart.properties.ListenerAndStarterProperties;
import com.cebon.restart.utils.CommendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * @Auther: LiaoPeng
 * @Date: 2019/5/31
 */
@Component
public class ListenerAndStarter implements ApplicationRunner {

    @Autowired
    ListenerAndStarterProperties prop;

    String pid;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(prop.getIsOpen()){
            new Thread(()->{
                while(true){
                    String result = CommendUtil.exec(prop.getListenCmd());
                    if(result!=null){
                        int length = result.length();
                        pid = result.substring(length-6,length).trim();
                        System.out.println(pid);
                    }
                    if(result == null){
                        System.out.println("closeAndStart--------------");
//            CommendUtil.exec(prop.getCloseCmd());
                        if(pid!=null){
                            CommendUtil.exec("cmd /c taskkill /f /t /PID "+pid);
                        }

                        String floder = prop.getFloder();
                        String cmd = "cmd /c "+floder.substring(0,2)+"&cd "+prop.getFloder()+"&"+prop.getStart();
                        CommendUtil.exec(cmd);
                        pid = null;
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
