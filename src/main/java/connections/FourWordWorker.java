package connections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FourWordWorker implements WorkerInterface{

    List<String> wordGroup = new ArrayList<>();
    private int submissionNumber;
    private AppController controller;

    private List<List<String>> wordBank = new ArrayList<>();

    public FourWordWorker(AppController controller){
            this.controller=controller;
    }

    public FourWordWorker(){
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

        for (List<String> sublist : wordBank) {
            copyOfBank.add(new ArrayList<>(sublist));
        }

        for(int i=0; i<4; i++){
            int index = random.nextInt(1,copyOfBank.size());
            
            for(int a=0; a<4; a++){
                int index1 = random.nextInt(1,(copyOfBank.get(index).size()));
                newcategories.add(copyOfBank.get(index).get(index1));
                copyOfBank.get(index).remove(index1);
                }
            copyOfBank.remove(index);
        }
        Collections.shuffle(newcategories);
        return newcategories;
    }

    @Override
    public void checkSubmission(AppController controller) {
        String category="";
            for (List<String> lst : wordBank){ //går gjennom fasit banken
                int wordsFoundInList=0;
                
                for (String submittedWord : wordGroup){ //går gjennom ordene i submission listen

                    outerloop:
                    for(String word : lst){ //sammenligner med alle ordene i fasit listen
                        if (submittedWord ==word ){ //hvis ordene matcher
                            wordsFoundInList++;
                            break outerloop;
                        }
                    }
                }
                if (wordsFoundInList==4){
                    category=lst.get(0);
                    controller.correctSubmission(category, this.wordGroup, submissionNumber);
                    setSubmissionNumber(this.submissionNumber+1);
                    //correct submission
                    this.wordGroup.clear();
                    return;
                }
            }
            controller.incorrectSubmission();
            wordGroup.clear();
            //incorrect submission
            //empty list, skriv noe på toppen
    }

    @Override
    public void wordTouched(String word) {
        if(!isInGroup(word)){
            addtoWordGroup(word);
        }
        else{
            removefromWordGroup(word);
        }
        //System.out.println("In submission: " + wordGroup);
    }

    @Override
    public void addtoWordGroup(String word) {
        wordGroup.add(word);
    }

    @Override
    public void removefromWordGroup(String word) {
        wordGroup.remove(word);
    }

    @Override
    public boolean isInGroup(String word) {
        return wordGroup.contains(word);
    }

    public void setSubmissionNumber(int number){
        this.submissionNumber=number;
    }

    public List<String> getWordGroup() {
        return wordGroup;
    }
    
}
