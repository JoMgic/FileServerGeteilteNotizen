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
        notizen = new Notizbuch();
    }
    public Server(String s){
        outClientList = clientErkennung("out");
        //inClientList = clientErkennung("in");
        dir = s;
        System.out.println("");
        notizen = new Notizbuch();
    }
    public void startThread(){
        Server s = new Server();
        Thread t = new Thread(s);
        t.start();
        s = null;
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
        }catch(Exception e){ };
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
            clientList.add(new FileSupervisor(new File("./dat/cl." + i + "." + endung)));
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
        while(true){
            if(fileS.isChanged()){
                System.out.println("Veraenderung erkannt");
                Cmd cmd = new Cmd(FileManager.readFirstLine(fileS.getFile().toString()));
                cmd.exec();
            }
        }
    }
    public static void addNotiz(String[] s){
        System.out.println("Es funktioniert!!! "+ s[0]);
        notizen.speichereNotiz(s[0]);
        alleNotizenAusgeben();
    }
    public static void sendNotiz(String[] s){
        //FileManager.writeFile(s[0], notizen.getNotiz(Integer.parseInt(s[1])));
        alleNotizenAusgeben();
        String value = notizen.getNotiz(Integer.parseInt(s[1]));
        FileManager.writeFile(dir + "cl."+s[0]+".in", value);
    }
    public static void alleNotizenAusgeben(){
        notizen.alleNotizenAusgeben();
    }
}
