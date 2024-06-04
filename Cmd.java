import java.lang.reflect.Method;

public class Cmd
{
    private String cmd = "<CMD><name>addnotiz</name><value>helloworld</value>";
    private String name;
    private String value;
    private String param[];
    /**
     * Konstruktor für Objekte der Klasse cmd
     */
    public Cmd(String command){
        
    }
    public Cmd(){
        
    }
    public String htmlEncoder(String param, String s){
        if(s == null || param == null){
            return "";
        }
        /*String[] tmp = s.split("<" + param + ">");
        String[] tmp2 = tmp[1].split("</" + param + ">");
        String value = tmp2[0];*/
        String value = s.split("<" + param + ">")[1].split("</" + param + ">")[0];
        System.out.println(param + ": " + value);
        return value;
    }
    public void exec(){
        cmd = "<CMD><name>addNotiz</name><value>helloworld</value>";
        name = htmlEncoder("name", cmd);
        value = htmlEncoder("value", cmd);
        
        param = new String[]{"Hpe", "World"};
        try {
            Method methode = Server.class.getMethod(name, String[].class);
            methode.invoke(new Server(), (Object)param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String addNotiz(String value){
        return "<CMD><addNotiz><value="+value+">";
        //return STR."<CMD><name=addNotiz><value=\{value}>";
    }
    public static String readFile(){
        return "<CMD><name>readNotiz</name><value></value>";
    }
}
