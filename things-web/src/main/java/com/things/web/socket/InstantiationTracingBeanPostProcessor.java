package com.things.web.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.xml.ws.WebFault;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if(nettyServer.isRun()){
                return;
            }

            nettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
