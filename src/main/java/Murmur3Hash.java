import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Murmur3Hash {

    public  static long hash(Object content) {
        HashFunction hf = Hashing.murmur3_128();

        return hf.hashBytes(content.toString().getBytes()).asLong();
    }


}
