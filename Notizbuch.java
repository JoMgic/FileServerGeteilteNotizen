import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

/**
 * @author Johannes
 */
public class Notizbuch
{
    private ArrayList <String> notizen;
    private String filename;
    public Notizbuch()
    {
        filename = "./dat/Notebook.nb";
        //notizen = new ArrayList<>();
        //FileManager.createFile(filename);
        fileLaden();
        //notizen = FileManager.readFile(filename);
    }
    public Notizbuch(ArrayList<String> arrlist){
        filename = "./dat/Notebook.nb";
        FileManager.createFile(filename);
        notizen = arrlist;
    }
    public Notizbuch(File f)
    {
        filename = f.toString();
        //notizen = new ArrayList<>();
        FileManager.createFile(filename);
        notizen = FileManager.readFile(filename);
    }
    public void speichereNotiz(String s){
        notizen.add(s);
        System.out.println("Notiz: " + s + " wurde gespeichert.");
        fileSpeichern();
        return;
    }
    public void speichereNotiz(int i, String value){
        notizen.add(i, value);
    }
    public int anzahlNotizen(){
        System.out.println("Anzahl Notizen: " + notizen.size());
        return notizen.size();
    }
    public String zeigeNotizen(int notiznummer){
        if(notiznummer <= notizen.size()-1 && notiznummer >= 0){
            System.out.println("Notize: " + notizen.get(notiznummer));
            return notizen.get(notiznummer);
        }
        return "EXIT_FAILURE";
    }
    public void entferneNotiz(int notiznummer){
        if(notiznummer <= notizen.size()-1 && notiznummer >= 0){
            System.out.println("Folgende Notiz wurde entfernt: " + notizen.remove(notiznummer));
            fileSpeichern();
        }
        return;
    }
    public void alleNotizenAusgebenMitForEach(){
        System.out.println("Notizen mit ForEach:");
        for(String s : notizen){
            System.out.println(s);
        }
        System.out.println("");
    }
    public void alleNotizenAusgeben(){
        System.out.println("Notizen mit For:");
        for(int i = 0; i < notizen.size(); i++){
            System.out.println(notizen.get(i));
        }
        System.out.println("");
    }
    public void johannesTagesaufgaben(){
        speichereNotiz("Brot kaufen");
        speichereNotiz("Handy aufladen");
        speichereNotiz("Info-HA machen");
        entferneNotiz(1);
    }
    public int sucheNotiz(String notiz){
        for(int i = 0; i < notizen.size(); i++){
            if(notizen.get(i).contains(notiz)){
                System.out.println(i + ". " + notizen.get(i));
                return i;
            }
        }
        try{
            return Integer.parseInt(null);
        }catch(Exception e){
            return Integer.MIN_VALUE;
        }
    }
    public ArrayList<Integer> sucheNotizKomplett(String notiz){
        ArrayList<Integer> arrlist = new ArrayList<>();
        for(int i = 0; i < notizen.size(); i++){
            if(notizen.get(i).contains(notiz)){
                System.out.println(i + ". " + notizen.get(i));
                arrlist.add(i);
            }
        }
        return arrlist;
    }
    public int sucheNotizUebereinstimmung(String notiz, ArrayList <String> arrlist){
        for(int i = 0; i < arrlist.size(); i++){
            if(notizen.get(i).equals(notiz)){
                System.out.println(i + ". " + notizen.get(i));
                return i;
            }
        }
        try{
            return Integer.parseInt(null);
        }catch(Exception e){
            return Integer.MIN_VALUE;
        }
    }
    public boolean notizVorhanden(String notiz){
        for(String s : notizen){
            if(notiz.equals(s)){
                return true;
            }
        }
        return false;
    }
    public void alleNotizenAusgabenMitLambda(){
        notizen.forEach( (n) -> { System.out.println(n); } );
    }
    private ArrayList<String> getList(){
        return notizen;
    }
    /*private void fileSyncOLD(){
        //FileManager.deleteFile("./notizbuch.txt");
        if(FileManager.createFile("./notizbuch.txt")){
            for(String i : notizen){
                FileManager.writeFile("./notizbuch.txt", i);
                System.out.println(i + " to file");
            }
            System.out.println("Notizbuch wurde erstellt und Notizen wurden gespeichert");
        }
        ArrayList<String> oldNotes = FileManager.readFile("./notizbuch.txt");
        oldNotes.forEach( (n) -> { System.out.println(n); } );
        for(int i = 0; i < notizen.size(); i++){
            int a = sucheNotizUebereinstimmung(notizen.get(i), oldNotes);
            if(a >= 0 && a < oldNotes.size()){
                oldNotes.remove(a);
            }
        }
        for(String i : oldNotes){
            notizen.add(i);
            System.out.println(i + " wird gespeichert...");
        }
        
        alleNotizenAusgabenMitLambda();
        //FileManager.printFile("./notizbuch.txt");
    }*/
    public void fileSpeichern(){
        /*FileManager.deleteFile(filename);
        FileManager.createFile(filename);
        FileManager.writeFile(filename, notizen);
        System.out.println("Notizbuch gespeichert");*/
        FileManager.overrideFile(filename, notizen);
    }
    
    public void fileSync(){
        /*Notizbuch savedNotebook = new Notizbuch(FileManager.readFile(filename));
        for(String s : notizen){
            if(!savedNotebook.notizVorhanden(s)){
                System.out.println(s + "nicht vorhanden");
                savedNotebook.speichereNotiz(s);
                //FileManager.writeFile(filename, savedNotebook.getList());
            }
        }
        notizen = savedNotebook.getList();
        fileSpeichern();*/
        
    }
    
    
    public void fileLaden() {
        if (new File(filename).exists()) {
            notizen = FileManager.readFile(filename);
            System.out.println("File geladen: " + notizen.toString());
        } else {
            FileManager.createFile(filename);
            fileLaden();
        }
    }
    public void alleNotizenAusgebenMitIterator(){
        Iterator<String> iterator;                      // Iterator<String> iterator = notizen.iterator();
        iterator = notizen.iterator();
        System.out.println("Notizen mit Iterator:");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("");
    }
     public String getNotiz(int i) {
        if (i < notizen.size())
            return notizen.get(i);
        System.out.println("Notiz außerhalb des Bounds");
        return null;
    }
     public int getSize() {
        return notizen.size();
    }
}
