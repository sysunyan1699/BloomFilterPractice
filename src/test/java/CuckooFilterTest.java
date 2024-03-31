import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CuckooFilterTest {

    @Test
    public void containsTest() {

        CuckooFilter cf = new CuckooFilter();
        cf.insert(1);
        assertTrue(cf.lookup(1));
        cf.delete(1);
        assertFalse(cf.lookup(1));
    }

}
