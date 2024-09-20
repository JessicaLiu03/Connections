package connections;

import java.util.List;

public interface WorkerInterface {

    void getWordBank();

    void setupNewCategories();

    List<String> pickNewCategories();

    void checkSubmission(AppController controller);

    void wordTouched(String word);

    void addtoWordGroup(String word);

    void removefromWordGroup(String word);

    boolean isInGroup(String word);
    
}
