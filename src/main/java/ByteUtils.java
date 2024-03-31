public class ByteUtils {

    //按位与运算（& 0xff）来确保每个字节在转换为long类型时是无符号的，
    //然后通过按位或运算（|）和按位左移（<<）操作来组合这些字节到一个long类型的值中。
    //在大端序中，字节数组的第一个字节是long值的最高有效字节（MSB），最后一个字节是最低有效字节（LSB）。
    public static long bytesToLongBigEndian(byte[] bytes) {
        if (bytes.length < 8) {
            throw new IllegalArgumentException("Byte array too short!");
        }
        return ((long) bytes[0] << 56) |
                ((long) bytes[1] & 0xff) << 48 |
                ((long) bytes[2] & 0xff) << 40 |
                ((long) bytes[3] & 0xff) << 32 |
                ((long) bytes[4] & 0xff) << 24 |
                ((long) bytes[5] & 0xff) << 16 |
                ((long) bytes[6] & 0xff) <<  8 |
                ((long) bytes[7] & 0xff);
    }
    //在小端序中，字节数组的第一个字节是long值的最低有效字节（LSB），最后一个字节是最高有效字节（MSB）
    public static long bytesToLongLittleEndian(byte[] bytes) {
        if (bytes.length < 8) {
            throw new IllegalArgumentException("Byte array too short!");
        }
        return ((long) bytes[7] << 56) |
                ((long) bytes[6] & 0xff) << 48 |
                ((long) bytes[5] & 0xff) << 40 |
                ((long) bytes[4] & 0xff) << 32 |
                ((long) bytes[3] & 0xff) << 24 |
                ((long) bytes[2] & 0xff) << 16 |
                ((long) bytes[1] & 0xff) <<  8 |
                ((long) bytes[0] & 0xff);
    }

    //大端序意味着字节数组的第一个字节代表int值的最高有效字节（MSB），而最后一个字节是最低有效字节（LSB）。
    public static int bytesToIntBigEndian(byte[] bytes) {
        if (bytes.length < 4) {
            throw new IllegalArgumentException("Byte array too short!");
        }
        return (bytes[0] & 0xFF) << 24 |
                (bytes[1] & 0xFF) << 16 |
                (bytes[2] & 0xFF) << 8 |
                (bytes[3] & 0xFF);
    }


    //小端序意味着字节数组的第一个字节代表int值的最低有效字节（LSB），而最后一个字节是最高有效字节（MSB）
    public static int bytesToIntLittleEndian(byte[] bytes) {
        if (bytes.length < 4) {
            throw new IllegalArgumentException("Byte array too short!");
        }
        return (bytes[3] & 0xFF) << 24 |
                (bytes[2] & 0xFF) << 16 |
                (bytes[1] & 0xFF) << 8  |
                (bytes[0] & 0xFF);
    }


}
