import java.io.File;
import java.util.Scanner;

public class FileReader {

    public String[] createDataFromFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                line = line.replaceAll("[\n\t\s]", " ");
                sb.append(line).append("\n");

            }
        } catch (Exception e) {
            System.out.printf("File [%s] is not found.\n", path);
            System.out.println(e.getMessage());
        }

        String[] array = sb.toString().split("\n");

        return array;
    }
}
