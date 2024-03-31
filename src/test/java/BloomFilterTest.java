import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BloomFilterTest {


    @Test
    public void mightContains() {
        BloomFilter bl = new BloomFilter(100, 3);
        bl.add(3);
        assertTrue(bl.mightContains(3));
        assertFalse(bl.mightContains("ok"));
    }
}
