import java.io.File;

/**
 * Beschreiben Sie hier die Klasse Client.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Client
{
    int number;
    File oFile;
    String dir;
    public Client(){
        dir = "./dat/";
        number = Server.getNewClientNumber();
        FileManager.createFile(dir + "cl." +  number + ".out");
        FileManager.createFile(dir + "cl." + number + ".in");
        oFile = new File(dir + "cl." + number + ".out");
    }
    public Client(String s){
        dir = s;
        number = Server.getNewClientNumber();
        FileManager.createFile(dir + "cl." +  number + ".out");
        FileManager.createFile(dir + "cl." + number + ".in");
        oFile = new File(dir + "cl." + number + ".out");
    }
    public void addNotiz(String s){
        FileManager.writeFile(oFile.toString(), "<Cmd><name>addNotiz</name><arg0>"+s+"</arg0><arg1>test</arg1></Cmd>");
    }
    public void readNotiz() throws InterruptedException {
        FileManager.writeFile(oFile.toString(), "<Cmd><name>sendNotiz</name><arg0>"+number+"</arg0><arg1>0</arg1></Cmd>");
        Thread.sleep(500);
        FileManager.printFile(dir + "cl."+number+".in");
    }
}
