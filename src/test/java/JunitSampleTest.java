import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class JunitSampleTest {

    @Test
    public void sampleStringTest() {
        String testString = "CodersLabJava";

        boolean condition = testString.startsWith("Co");
        System.out.println(condition);
        assertTrue(condition);
    }

    @Test
    public void sampleListTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        assertEquals(4, list.size());
        assertEquals(Integer.valueOf(2), list.get(1));
    }

    @Test
    public void myClass() {
        String parameter = "Damian";

        MyClass myClass = new MyClass(parameter);
        String sample = myClass.getSample();
        assertEquals(parameter + "sample", sample);
        System.out.println(sample);
    }
}
