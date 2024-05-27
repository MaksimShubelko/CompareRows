import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static FileReader fileReader = new FileReader();
    private static String LINE_START = "RedirectMatch";
    private static String PRODUCT_REGEX = "/p/*";
    private static String CATEGORY_REGEX = "/c/*";

    public static void main(String[] args) throws IOException {
        boolean isContains;
        String[] newData = fileReader.createDataFromFile("new");
        String[] oldData = fileReader.createDataFromFile("old");

        for (String rowNew : newData) {
            isContains = false;
            for (String rowOld : oldData) {
//                use to find equals products in akamai and new data
//
//                if (rowNew.split(" ")[2].split("/p/")[1].replace("\"", "")
//                        .equals(rowOld.split(" ")[2].split("/p/")[1].split("\\?")[0].replaceAll("[\"/]", "").replaceAll("BP_", ""))) {
//                    isContains = true;
//                    System.out.println(rowNew.split(" ")[2].split("/p/")[1].replace("\"", ""));
//                    System.out.println(rowOld.split(" ")[2].split("/p/")[1].split("\\?")[0].replaceAll("[\"/]", "").replaceAll("BP_", ""));
//                }

                if (rowNew.equals(rowOld)) {
                    isContains = true;
                }
            }
            if (!isContains) {
                String[] parts = rowNew.split(" ");
//                if (!parts[0].contains("/p/")) {
//                    System.out.println("####################################################");
//                    System.out.println(rowNew);
//                    System.out.println("####################################################");
//
//                }
                String newResultString = LINE_START + " " + parts[1] + " " + "\"(.*)/p/" + parts[0].split("/p/")[1] + "\" " + resolveString(parts[2]);

                System.out.println(newResultString);
            }
        }

    }

    public static String resolveString(String data) {
        if (data.equals("/")) {
            return "\"/\"";
        }

        if (data.contains("/c/")) {
            return "\"/с/" + data.split("/c/")[1] + "\"";
        }

        if (data.contains("/b/")) {
            return "\"/b/" + data.split("/b/")[1] + "\"";
        }

        if (data.contains("/bc/")) {
            return "\"/bс/" + data.split("/bc/")[1] + "\"";
        }

        if (data.contains("/p/")) {
            return "\"/p/" + data.split("/p/")[1] + "\"";
        }

        return "";

    }
}



