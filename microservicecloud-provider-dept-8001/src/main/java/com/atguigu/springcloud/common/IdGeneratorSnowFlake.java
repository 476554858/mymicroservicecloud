package com.atguigu.springcloud.common;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IdGeneratorSnowFlake {
    private long workerId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void init(){
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId =  NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflokeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId,long  datacenterId){
        Snowflake snowflake  = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowFlake().snowflokeId());
    }
}
