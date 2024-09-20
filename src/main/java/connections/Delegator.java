package connections;

import java.util.Arrays;
import java.util.List;

public class Delegator {

    private WorkerInterface worker;

    public void delegateAccordingToGroupSize(String groupsize, AppController controller){
        List<String> indexes = Arrays.asList("0","1","2","3", "4");
        int wordGroupSize = indexes.indexOf(groupsize);
        if(wordGroupSize==2){
            this.worker = new TwoWordWorker(controller);
            //System.out.println("This.worker: " +  this.worker);
            
        }
        if(wordGroupSize==3){
            worker = new ThreeWordWorker(controller);
        }
        if(wordGroupSize==4){
            worker = new FourWordWorker(controller);
        }
        this.worker.getWordBank();
        this.worker.setupNewCategories();
        }

    public void delegateWordAction(String word){
        this.worker.wordTouched(word);
    }

    public void delegateSubmission(AppController controller){
        this.worker.checkSubmission(controller);
    }

    public WorkerInterface getWorker(){
        return worker;
    }

}
