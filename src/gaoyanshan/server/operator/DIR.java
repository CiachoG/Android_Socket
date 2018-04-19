package gaoyanshan.server.operator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DIR extends Operator{

    ArrayList<String> msgBackList;


    @Override
    public ArrayList<String> exeDir(String cmdBody) throws Exception {
        File file = new File(cmdBody);
        file.setExecutable(true);//设置可执行权限
        file.setReadable(true);//设置可读权限
        file.setWritable(true);//设置可写权限
        File[] listFiles = file.listFiles();
        msgBackList = new ArrayList<String>();
        for (File mfile : listFiles) {
            if (mfile.isHidden() == false) {
                String fileName = mfile.getName();
                long lastModified = mfile.lastModified();//获取文件修改时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//给时间格式，例如：2018-03-16 09:50:23
                String fileDate = dateFormat.format(new Date(lastModified));//取得文件最后修改时间，并按格式转为字符串
                String fileSize = "0";
                String isDir = "1";
                if (!mfile.isDirectory()) {//判断是否为目录
                    isDir = "0";
                    fileSize = "" + mfile.length();
                }
                msgBackList.add(fileName + ">" + fileDate + ">" + fileSize + ">" + isDir + ">");
            }


        }
        return msgBackList;
    }

}

