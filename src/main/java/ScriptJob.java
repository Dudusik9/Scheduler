import jdk.nashorn.tools.Shell;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.IOException;


public class ScriptJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String tmp = jobExecutionContext.getJobDetail().getJobDataMap().getString("id");
//            tmp = tmp.replace("\\", "\\\\");
            String resultCommand = String.format("start %s", tmp);
            Process process = new ProcessBuilder("cmd.exe","/c", resultCommand).start();
//            Process process = new ProcessBuilder("cmd.exe", "/c" , tmp).start();
//            Process process = Runtime.getRuntime().exec("start cmd /k" + tmp);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Script don't find!");
        }
    }
}
