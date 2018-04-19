package gaoyanshan.server.operator;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

public class Connetct extends Operator{
    @Override
    public ArrayList<String> exeDir(String cmdBody) throws Exception {
        ArrayList<String> msgBackList=new ArrayList<>();
        FileSystemView sys = FileSystemView.getFileSystemView();
        File[] files = sys.getRoots();
        files = File.listRoots();
        for (int i = 0; i < files.length; i++) {
            msgBackList.add(files[i].toString());
             //sys.getSystemTypeDescription(files[i]);
        }
        return msgBackList;
    }
}
