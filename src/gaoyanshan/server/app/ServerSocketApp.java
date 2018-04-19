package gaoyanshan.server.app;

import gaoyanshan.server.socket.CmdServerSocket;

import java.io.IOException;

public class ServerSocketApp {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int isClose=1;
        try {
            if(isClose==1){
                new CmdServerSocket().work();
                isClose=0;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
