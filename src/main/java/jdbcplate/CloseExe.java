package jdbcplate;

public class CloseExe {


    public static void close(AutoCloseable... autoCloseable) {
        for (AutoCloseable closeable : autoCloseable) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
