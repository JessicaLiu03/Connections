package connections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ConnectionsTest {

    @Test
    public void testFileReading(){
        Save save = new Save();
        assertTrue(save.getFromFile() instanceof List<List<String>>);
        assertEquals(save.getFromFile().size(),25);
    }

    @Test
    public void testFileWriting() {
/*         Save save = new Save();
        save.writeToFile(Arrays.asList("Phone brands, samsung,nokia,apple,blackberry"));
        assertEquals(save.getFromFile().size(),26); */
    }

    @Test
    public void testWorkers(){
        TwoWordWorker worker2 = new TwoWordWorker();
        assertTrue(worker2.getWordGroup().isEmpty());
        worker2.addtoWordGroup("hei");
        assertEquals(worker2.getWordGroup(), Arrays.asList("hei"));
        worker2.removefromWordGroup("hei");
        assertTrue(worker2.getWordGroup().isEmpty());
    }

    @Test
    public void testWordBank(){
        TwoWordWorker worker2 = new TwoWordWorker();
        assertTrue(worker2.returnWordBank().isEmpty());
        worker2.getWordBank();
        assertEquals(worker2.returnWordBank().size(), 25);
    }

    @Test
    public void testPickCategories(){
        TwoWordWorker worker2 = new TwoWordWorker();
        worker2.getWordBank();
        assertEquals(worker2.pickNewCategories().size(), 8);
        ThreeWordWorker worker3 = new ThreeWordWorker();
        worker3.getWordBank();
        assertEquals(worker3.pickNewCategories().size(), 12);
        FourWordWorker worker4 = new FourWordWorker();
        worker4.getWordBank();
        assertEquals(worker4.pickNewCategories().size(), 16);
    }

    @Test
    public void testWordTouched(){
        TwoWordWorker worker2 = new TwoWordWorker();
        worker2.wordTouched("hei");
        assertEquals(worker2.getWordGroup(), Arrays.asList("hei"));
        worker2.wordTouched("hei");
        assertTrue(worker2.getWordGroup().isEmpty());
    }
}
