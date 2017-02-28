import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTextReader {

    public static void main(String[] args) {
        try {
            MethodStorage.initMethodStorage( Files.readAllLines(
                    Paths.get(args[0]), StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Invalid path");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter path");
        }

    }

}
