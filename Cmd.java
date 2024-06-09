import java.lang.reflect.Method;

public class Cmd
{
    private String cmd = "<CMD><name>addnotiz</name><value>helloworld</value>";
    public final static String bsp = "<CMD><name>addNotiz</name><arg0>hallo</arg0><arg1>test</arg1>";
    private String name;
    private String value;
    private String param[];
    public Cmd(String command){
        cmd = command;
    }
    public Cmd(){
        cmd = "<CMD><name>addNotiz</name><value>helloworld</value><arg0>hallo</arg0><arg1>test</arg1>";
    }
    public static String htmlEncoder(String param, String s){
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
        
        name = htmlEncoder("name", cmd);
        //value = htmlEncoder("value", cmd);
        
        param = htmlEncoderParameter(cmd);
        //param[0] = htmlEncoder("arg0", cmd);
        //param[1] = htmlEncoder("arg1", cmd);
        //System.out.println(param.toString());
        try {
            Method methode = Server.class.getMethod(name, String[].class);
            methode.invoke(new Server(), (Object)param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EXECUTION");
    }
    public static String addNotiz(String value){
        return "<CMD><addNotiz><value="+value+">";
        //return STR."<CMD><name=addNotiz><value=\{value}>";
    }
    public static String readFile(){
        return "<CMD><name>readNotiz</name><value></value>";
    }
    public static String[] htmlEncoderParameter(String s){
        if(s == null){
            return new String[]{null};
        }
        String[] arr;
        int arrSize = 0;
        for(int i = 0; s.contains("</arg"+i+">"); i++){
            arrSize = i+1;
        }
        System.out.println("ArraySize" + arrSize);
        arr = new String[arrSize];
        for(int i = 0; i < arrSize; i++){
            System.out.println("start encoding " + i);
            arr[i] = htmlEncoder("arg"+i, s);
        }
        return arr;
    }
}
