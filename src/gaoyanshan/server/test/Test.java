package gaoyanshan.server.test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FileSystemView sys = FileSystemView.getFileSystemView();
        File[] files = sys.getRoots();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
        files = File.listRoots();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i] + " -- "
                    + sys.getSystemTypeDescription(files[i]));
        }

    }
}
