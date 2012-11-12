package com.rpc.test;

import com.rpc.RpcFramework;

/**
* RpcProvider
*
* @author william.liangf
*/
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}