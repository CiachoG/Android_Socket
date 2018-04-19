package gaoyanshan.server.socket;

import gaoyanshan.server.operator.Connetct;
import gaoyanshan.server.operator.DIR;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CmdServerSocket {

    int port=8019;// 自定义一个端口，端口号尽可能挑选一些不被其他服务占用的端口，祥见http://blog.csdn.net/hsj521li/article/details/7678880
    static int connect_count=0;//连接次数统计
    OutputStreamWriter writer;
    BufferedReader bufferedReader;
    ArrayList<String> msgBackList;
    ArrayList<String> msgList;
    String set;
    public CmdServerSocket() {
        // TODO Auto-generated constructor stub
    }

    public CmdServerSocket(int port) {
        super();

        this.port = port;
    }

    public void work() throws Exception {
        // 注意：由于Socket的工作是阻塞式，Android端Socket的工作必须在新的线程中实现，若在UI主线程中工作会报错

        @SuppressWarnings("resource")
        ServerSocket serverSocket=new ServerSocket(port);
        while(true){//无限循环，使之能结束当前socket服务后，准备下一次socket服务

            System.out.println("Waiting client to connect.....");
            Socket socket = serverSocket.accept();//阻塞式，直到有客户端连接进来，才会继续往下执行，否则一直停留在此代码
            InetAddress inetAddress = socket.getInetAddress();
            ArrayList<String> a=new Connetct().exeDir("dsad");
            System.out.println(a);
            readBackMsg(socket);

            writeBackMsg(socket);
            //System.out.println(readSocketMsg(socket));


            //OutputStreamWriter writer=new OutputStreamWriter(os);//默认的字符编码，有可能是GB2312也有可能是UTF-8，取决于系统
            ////建议不要用默认字符编码，而是指定UTF-8，以保证发送接收字符编码一致，不至于出乱码
            //输出流是字节传输的，还不具备字符串直接写入功能，因此再将其封装入OutputStreamWriter，使其支持字符串直接写入
            bufferedReader.close();
            socket.close();
            System.out.println("当前Socket服务结束");
        }

    }
    public ArrayList<String> readSocketMsg(Socket socket) throws IOException {
        // 读socket的输入流，传入的socket参数是已经连接成功未处于关闭的socket
        //首先读取一行，并将读取的字符串内容转换为int型数据，已获得后续需要读取的行数
        ArrayList<String> msgList=new ArrayList<String>();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
        bufferedReader=new BufferedReader(reader);
        String lineNumStr = bufferedReader.readLine();
        int lineNum=Integer.parseInt(lineNumStr);
        for(int i=0;i<lineNum;i++){
            String str = bufferedReader.readLine();
            msgList.add(str);
        }
        //读取结束后，输入流不能关闭，此时关闭，会将socket关闭，从而导致后续对socket写操作无法实现

        return msgList;
    }
    private void writeBackMsg(Socket socket) throws Exception {
        // TODO Auto-generated method stub
        BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());//socket.getOutputStream()是输出流，BufferedOutputStream则将其封装为带缓冲的输出流

        //			OutputStreamWriter writer=new OutputStreamWriter(os);//默认的字符编码，有可能是GB2312也有可能是UTF-8，取决于系统
        //			//建议不要用默认字符编码，而是指定UTF-8，以保证发送接收字符编码一致，不至于出乱码
        //输出流是字节传输的，还不具备字符串直接写入功能，因此再将其封装入OutputStreamWriter，使其支持字符串直接写入
        msgBackList=new ArrayList<>();
        msgBackList=new DIR().exeDir(set);
        writer=new OutputStreamWriter(os,"UTF-8");//尝试将字符编码改成"GB2312"
        writer.write(set+"\n");
        System.out.println("set:"+set);
        writer.flush();
        writer.write(""+msgBackList.size()+"\n");//未真正写入的输出流，仅仅在内存中
        writer.flush();//写入输出流，真正将数据传输出去
        for(int i=0;i<msgBackList.size();i++){
            writer.write(msgBackList.get(i)+"\n");
            writer.flush();
        }
    }
    private void readBackMsg(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
        bufferedReader=new BufferedReader(reader);
        String num=bufferedReader.readLine();
        System.out.println(num);
        if(num!=null){
            set = bufferedReader.readLine();
        }

        System.out.println(set);


    }

}
