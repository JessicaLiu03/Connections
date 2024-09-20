package connections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TwoWordWorker implements WorkerInterface{

    private List<String> wordGroup = new ArrayList<>();
    private int submissionNumber;
    private AppController controller;

    private List<List<String>> wordBank = new ArrayList<>();

    public TwoWordWorker(AppController controller){
        this.controller=controller;
    }

    public TwoWordWorker(){
    }

    @Override
    public void setupNewCategories() {
        List<String> newCategories = this.pickNewCategories();
        int boxIndexForController=0;
        for(String a : newCategories){
            controller.setBox(a, boxIndexForController);
            boxIndexForController+=1;
            }
        controller.revealBoxes();
    }

    @Override
    public void getWordBank(){
        Save save = new Save();
        List<List<String>> wordBank = save.getFromFile();
        if (wordBank.size()==0){
            controller.handleNewGame();
        }
        else{
            this.wordBank=wordBank;
            }
    }

    @Override
    public List<String> pickNewCategories() {

        List<String> newcategories = new ArrayList<>();
        Random random = new Random();
        List<List<String>> copyOfBank = new ArrayList<>();

        for (List<String> sublist : this.wordBank) {
            copyOfBank.add(new ArrayList<>(sublist));
        }

        for(int i=0; i<4; i++){
            int index = random.nextInt(1,copyOfBank.size());

            for(int a=0; a<2; a++){
                int index1 = random.nextInt(1,(copyOfBank.get(index).size()));
                newcategories.add(copyOfBank.get(index).get(index1));
                copyOfBank.get(index).remove(index1);
                }
            copyOfBank.remove(index);
        }
        Collections.shuffle(newcategories);
        return newcategories;
    }


    public void checkSubmission(AppController controller){
        String category="";
            for (List<String> lst : wordBank){
                int wordsFoundInList=0;
                
                for (String submittedWord : wordGroup){

                    outerloop:
                    for(String word : lst){
                        if (submittedWord ==word ){
                            wordsFoundInList++;
                            break outerloop;
                        }
                    }
                }
                if (wordsFoundInList==2){
                    category=lst.get(0);
                    controller.correctSubmission(category, this.wordGroup, submissionNumber);
                    setSubmissionNumber(this.submissionNumber+1);
                    this.wordGroup.clear();
                    return;
                }
            }
            controller.incorrectSubmission();
            wordGroup.clear();
    }

    public void wordTouched(String word) {
        if(!isInGroup(word)){
            addtoWordGroup(word);
        }
        else{
            removefromWordGroup(word);
        }
    }

    public void addtoWordGroup(String word) {
        wordGroup.add(word);
    }

    public void removefromWordGroup(String word) {
        wordGroup.remove(word);
    }

    public boolean isInGroup(String word) {
        return wordGroup.contains(word);
    }

    public void setSubmissionNumber(int number){
        this.submissionNumber=number;
    }

    public List<String> getWordGroup() {
        return wordGroup;
    }

    public List<List<String>> returnWordBank() {
        return wordBank;
    }
}
