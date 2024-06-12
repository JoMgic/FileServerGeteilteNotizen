import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.IOException;
import java.nio.file.attribute.*;
import java.io.File;
import java.util.ArrayList;

public class Server implements Runnable{
    private ArrayList<FileSupervisor> outClientList;
    private ArrayList<File> inClientList;
    private static Notizbuch notizen;
    private static String dir;
    public Server(){
        outClientList = clientErkennung("out");
        //inClientList = clientErkennung("in");
        dir = "./dat/";
        System.out.println("");
        
    }
    public Server(String s){
        outClientList = clientErkennung("out");
        //inClientList = clientErkennung("in");
        dir = s;
        System.out.println("");
        notizen = new Notizbuch();
        notizen.speichereNotiz("Hallo Malina");
    }
    public void startThread(){
        Server s = new Server();
        Thread t = new Thread(s);
        t.start();
        s = null;
        notizen = new Notizbuch();
        outClientList = clientErkennung("out");
    }
    @Override
    public void run(){
        try{
            System.out.println("Starte Listener");
            for(FileSupervisor fs : outClientList){
                new Thread(){
                    @Override
                    public void run(){
                        try
                        {
                            watchFileForChanges(fs);
                        }
                        catch (IOException | InterruptedException e)
                        {
                            e.printStackTrace();
                        }        
                    }
                }.start();
            }
        }catch(Exception e){
            System.out.println("Listener abgestürzt");
        };
    }
    public void listClients(){
        System.out.println("Aktive Clients");
        for(FileSupervisor fs : outClientList){
            File f = fs.getFile();
            System.out.println(f);
        }
    }
    private ArrayList<FileSupervisor> clientErkennung(String endung){
        ArrayList<FileSupervisor> clientList = new ArrayList<>();
        int i = 0;
        while(clientNumberExist(i)){
            clientList.add(new FileSupervisor(new File(dir+"cl." + i + "." + endung)));
            System.out.println("Client: cl." + i + " wurde erkannt");
            i++;
        }
        
        return clientList;
    }
    private static boolean clientNumberExist(int i){
        return new File(dir + "cl." + i + ".out").exists();
    }
    public static int getNewClientNumber(){
        int i = 0;
        while(clientNumberExist(i)){
            i++;
        }
        return i;
    }
    public void watchFileForChanges(FileSupervisor fileS) throws IOException, InterruptedException {
        Cmd cmd = new Cmd(Cmd.bsp);
        System.out.println("Initialisierung " + fileS.getFile().toString());
        while(true){
            if(fileS.isChanged()){
                System.out.println("Veraenderung erkannt");
                //cmd = new Cmd(FileManager.readFirstLine(fileS.getFile().toString()));
                cmd.newCommand(FileManager.readFirstLine(fileS.getFile().toString()));
                cmd.exec();
                Thread.sleep(50);
            }
        }
    }
    public static void addNotiz(String[] s){
        System.out.println("Es funktioniert!!! "+ s[0]);
        notizen.speichereNotiz(s[0]);
        alleNotizenAusgeben();
        FileManager.clearFile(dir + "cl."+s[0]+".out");
    }
    public static void sendNotiz(String[] s){
        //FileManager.writeFile(s[0], notizen.getNotiz(Integer.parseInt(s[1])));
        alleNotizenAusgeben();
        System.out.print(s);
        String value = notizen.getNotiz(Integer.parseInt(s[1]));
        FileManager.writeFile(dir + "cl."+s[0]+".in", value);
        FileManager.clearFile(dir + "cl."+s[0]+".out");
    }
    public static void alleNotizenAusgeben(){
        notizen.alleNotizenAusgeben();
    }
    public void requestNoteBookSize(String[] s){
        String value = Integer.toString(notizen.getSize()) ;
        FileManager.writeFile(dir + "cl."+s[0]+".in", value);
        FileManager.clearFile(dir + "cl."+s[0]+".out");
    }
    public void saveNotiz(String[] s){
        notizen.speichereNotiz(Integer.parseInt(s[0]), s[1]);
    }
}
