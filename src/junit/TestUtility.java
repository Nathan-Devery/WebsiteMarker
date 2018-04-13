package junit;

import com.steadystate.css.parser.CSSOMParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;

public class TestUtility {

    //TODO make all other tests (including the ones in other packages) make use of the 'throws' keyword.

    public static ArrayList<Document> getDocument(String fileName){   //Duplicated method, create a utility class?
        File file = new File("src/conditionTestFiles/" + fileName + ".html");
        try {
            Document doc = Jsoup.parse(file, "UTF-8", fileName + ".html");  //The last argument (location) is used for filename, maybe this is bad?
            ArrayList toReturn = new ArrayList();
            toReturn.add(doc);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Document> getXMLDocument(String fileName){   //Duplicated method, create a utility class?
        File file = new File("src/conditionTestFiles/" + fileName + ".html");
        try {
            Document doc = Jsoup.parse(fileToString(file), file.getName(), org.jsoup.parser.Parser.xmlParser());
            ArrayList toReturn = new ArrayList();
            toReturn.add(doc);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static CSSStyleSheet getCss(String fileName){
        File file = new File("src/conditionTestFiles/" + fileName + ".css");
        try {
            InputStream stream = new FileInputStream(file);
            InputSource source = new InputSource(new InputStreamReader(stream));
            CSSOMParser parser = new CSSOMParser();
            CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);  //These can be stored
            return stylesheet;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getCssString(String fileName){
        File file = new File("src/conditionTestFiles/" + fileName + ".css");
        return fileToString(file);
    }

    private static String fileToString(File file){
        String result = "";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
