import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    int number;
    File oFile;
    String dir;

    public Client() {
        dir = "./dat/";
        number = Server.getNewClientNumber();
        FileManager.createFile(dir + "cl." + number + ".out");
        FileManager.createFile(dir + "cl." + number + ".in");
        oFile = new File(dir + "cl." + number + ".out");
    }

    public String readNotiz(int i) throws InterruptedException {
        FileManager.writeFile(oFile.toString(), "<Cmd><name>sendNotiz</name><arg0>" + number + "</arg0><arg1>" + i + "</arg1></Cmd>");
        Thread.sleep(20);
        FileManager.printFile(dir + "cl." + number + ".in");
        return FileManager.readFirstLine(dir + "cl." + number + ".in");
    }

    public int requestNoteBookSize() {
        try {
            FileManager.writeFile(oFile.toString(), "<Cmd><name>requestNoteBookSize</name><arg0>" + number + "</arg0></Cmd>");
            Thread.sleep(20);
            FileManager.printFile(dir + "cl." + number + ".in");
            return Integer.parseInt(FileManager.readFirstLine(dir + "cl." + number + ".in"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.MIN_VALUE;
    }
    public void saveNotiz(int i, String value){
        FileManager.writeFile(oFile.toString(), "<Cmd><name>saveNotiz</name><arg0>"+i+"</arg0><arg1>"+value+"</arg1></Cmd>");
    }
    public void addNotiz(String s){
        FileManager.writeFile(oFile.toString(), "<Cmd><name>addNotiz</name><arg0>"+s+"</arg0></Cmd>");
    }
}
