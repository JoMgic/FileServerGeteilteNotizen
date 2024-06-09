import java.io.File;
import java.nio.file.attribute.FileTime;
import java.nio.file.Files;
public class FileSupervisor
{
    File f;
    FileTime lastChange;
    public FileSupervisor(File f){
        this.f = f;
        try
        {
            lastChange = Files.getLastModifiedTime(f.toPath());
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public boolean isChanged() throws java.io.IOException {
        if(!lastChange.equals( Files.getLastModifiedTime(f.toPath()))){
            System.out.println(lastChange + ":" + Files.getLastModifiedTime(f.toPath()));
            lastChange = Files.getLastModifiedTime(f.toPath());
            
            return true;
        }
        return false;
    }
    public File getFile(){
        return f;
    }
}
