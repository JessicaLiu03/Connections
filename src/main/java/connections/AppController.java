package connections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.util.stream.Collectors;

public class AppController {

    @FXML
    private AnchorPane mainpage;

    @FXML
    private Pane background;

    @FXML
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12, box13, box14, box15, box16;

    @FXML
    private List<CheckBox> allBoxes;

    @FXML
    private List<Label> allLabels;

    @FXML
    private List<TextField> allTextFields;

    @FXML
    private List<CheckBox> activeBoxes = new ArrayList<>();

    @FXML
    private Button newgame, groupsize2, groupsize3, groupsize4, submit;

    @FXML
    private Label howmanywords, incorrect, correct, submissionLabel1, submissionLabel2, submissionLabel3, submissionLabel4, invalid;

    @FXML
    private Delegator connection = new Delegator();

    @FXML
    private TextField categoryname, addedword1, addedword2, addedword3, addedword4;

    @FXML
    private void initialize() {
        List<CheckBox> allBoxes = Arrays.asList(box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12, box13, box14, box15, box16);
        this.allBoxes = allBoxes;

        List<Label> allLabels = Arrays.asList(submissionLabel1, submissionLabel2, submissionLabel3, submissionLabel4);
        this.allLabels = allLabels;

        List<TextField> allTextFields = Arrays.asList(categoryname, addedword1, addedword2,addedword3, addedword4);
        this.allTextFields=allTextFields;

        groupsize2.setVisible(false);
        groupsize3.setVisible(false);
        groupsize4.setVisible(false);
        howmanywords.setVisible(false);
        submit.setVisible(false);
        incorrect.setVisible(false);
        correct.setVisible(false);
        background.setVisible(false);
        invalid.setVisible(false);

        this.allBoxes.stream().forEach(checkbox -> checkbox.setVisible(false));
        this.allLabels.stream().forEach(label -> label.setVisible(false));
        AnchorPane.setTopAnchor(submissionLabel1, 145.0); // Set top anchor to 100
        AnchorPane.setLeftAnchor(submissionLabel1, 75.0); // Set left anchor to 50
        AnchorPane.setTopAnchor(submissionLabel2, 164.0); // Set top anchor to 100
        AnchorPane.setLeftAnchor(submissionLabel2, 75.0); // Set left anchor to 50
        AnchorPane.setTopAnchor(submissionLabel3, 183.0); // Set top anchor to 100
        AnchorPane.setLeftAnchor(submissionLabel3, 75.0); // Set left anchor to 50
        AnchorPane.setTopAnchor(submissionLabel4, 201.0); // Set top anchor to 100
        AnchorPane.setLeftAnchor(submissionLabel4, 75.0); // Set left anchor to 50
    }

    public void setBox(String word, int boxNumber){
        CheckBox box = this.allBoxes.get(boxNumber);
        box.setText(word);
        activeBoxes.add(box);
    }

    public void handleGroupSize(ActionEvent event){
        box2.setVisible(true);
        String groupsize = ((Button)event.getSource()).getText();
        connection.delegateAccordingToGroupSize(groupsize, this);
        newgame.setVisible(true);
        submit.setVisible(true);
        howmanywords.setVisible(false);
        groupsize2.setVisible(false);
        groupsize3.setVisible(false);
        groupsize4.setVisible(false);
        background.setVisible(true);
    }

    public void handleAdd(ActionEvent event){
        List<String> addedList = allTextFields.stream().map(TextField::getText).collect(Collectors.toList());
        System.out.println("List: " + addedList);
        for(String word : addedList){
            if(word.equals("") || word.contains(" ") || word==null){
                System.out.println("invalid add");
                resetAdd(true);
                return;
            }
        }
        Save saver = new Save(this);
        saver.writeToFile(addedList);
        resetAdd(false);
    }


    public void resetAdd(boolean valid){
        invalid.setVisible(valid);
        allTextFields.stream().forEach(field -> field.setText(""));
    }

    public void handle(ActionEvent event){
        CheckBox box = (CheckBox)event.getSource();
        String text = box.getText(); //til button
        this.connection.delegateWordAction(text);
        incorrect.setVisible(false);
        correct.setVisible(false);
        /* event.getSource();
        
        System.out.println(text); */
    }

    public void handlesubmit(){
        this.connection.delegateSubmission(this);
    }

    public void handleNewGame(){
        
        this.activeBoxes.stream().forEach(box -> box.setVisible(false));
        this.activeBoxes = new ArrayList<>();
        submit.setVisible(false);
        background.setVisible(false);
        
        AnchorPane.setTopAnchor(newgame, 415.0);
        AnchorPane.setLeftAnchor(newgame, 68.0);
        newgame.setVisible(false);

        AnchorPane.setTopAnchor(groupsize2, 177.0); // Set top anchor to 100
        AnchorPane.setLeftAnchor(groupsize2, 203.0); // Set left anchor to 50

        AnchorPane.setTopAnchor(groupsize4, 177.0); // Set top anchor to 150
        AnchorPane.setLeftAnchor(groupsize4, 276.0); // Set left anchor to 50

        AnchorPane.setTopAnchor(groupsize3, 177.0); // Set top anchor to 200
        AnchorPane.setLeftAnchor(groupsize3, 239.0); // Set left anchor to 50

        incorrect.setVisible(false);
        correct.setVisible(false);

        groupsize2.setVisible(true);
        groupsize3.setVisible(true);
        groupsize4.setVisible(true);

        howmanywords.setVisible(true);

        allBoxes.stream().forEach(box -> box.setSelected(false));
        allLabels.stream().forEach(box -> box.setVisible(false));
    }

    public void revealBoxes(){
        for(CheckBox box : activeBoxes){
            box.setVisible(true);
        }
    }

    public void incorrectSubmission(){
        incorrect.setVisible(true);
        correct.setVisible(false);
        activeBoxes.stream().forEach(box -> box.setSelected(false));
    }

    public void correctSubmission(String category, List<String> wordGroup, int display){
        String displaytext = "";
        correct.setVisible(true);
        incorrect.setVisible(false);
        displaytext+=category + " : ";
        for(String word : wordGroup){
            displaytext+= word + ", ";
            for (CheckBox box : activeBoxes){
                if(box.getText() == word){
                    box.setVisible(false);
                }
            }
        }
        allLabels.get(display).setText(displaytext);
        allLabels.get(display).setVisible(true);
    }
}
