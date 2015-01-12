package concurrencytasksample;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author tomo
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ProgressBar progressbarA;
    @FXML
    private ProgressBar progressbarB;
    @FXML
    private ProgressBar progressbarC;
    
    @FXML
    private void startTask(ActionEvent event){
        TaskSample taskA = new TaskSample();
        this.progressbarA.progressProperty().bind(taskA.progressProperty());
        TaskSample taskB = new TaskSample();
        this.progressbarB.progressProperty().bind(taskB.progressProperty());
        TaskSample taskC = new TaskSample();
        this.progressbarC.setProgress(0);
        taskC.setOnRunning(e -> {
            this.progressbarC.progressProperty().bind(taskC.progressProperty());
        });

        CompletableFuture<Void> futureA = CompletableFuture.runAsync(taskA);
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(taskB);
        futureA.runAfterBoth(futureB, taskC);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
