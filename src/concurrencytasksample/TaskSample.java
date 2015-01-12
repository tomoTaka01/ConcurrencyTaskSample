package concurrencytasksample;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.concurrent.Task;

/**
 *
 * @author tomo
 */
public class TaskSample extends Task<Void>{

    @Override
    protected Void call() throws Exception {
        updateProgress(0, 10);
        IntStream.rangeClosed(1, 10).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskSample.class.getName()).log(Level.SEVERE, null, ex);
            }
            updateProgress(i, 10);
        });
        return null;
    }
    
}
