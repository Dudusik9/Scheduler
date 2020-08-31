import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;


public class SchedulerRun
{
    public static final Logger logger = Logger.getLogger(SchedulerRun.class.getName());

    public static void main(String[] args) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            Map<Integer, Script> scripts = XMLParser.parsingConfigFile();
            logger.info("* * * * * * * Parsing XML file is done");
            for (int i = 1; i <= scripts.size(); i++) {
                MyScheduler currentScheduler = new MyScheduler(i, scripts.get(i).getPathToFile(), scripts.get(i).getLaunchTime(), scripts.get(i).getLaunchDay());
                scheduler.scheduleJob(currentScheduler.getJobDetail(), currentScheduler.getCronTrigger());
            }
            logger.info("Scheduler job is identified (JobDetail + CronTrigger)");
            scheduler.start();
            logger.info("Scheduler is started");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}