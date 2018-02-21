package junit;

import com.steadystate.css.parser.CSSOMParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.*;
import java.util.ArrayList;

public class TestUtility {

    public static ArrayList<Document> getDocument(String fileName){   //Duplicated method, create a utility class?
        File file = new File("src/testFiles/" + fileName + ".html");
        try {
            String currentDirectory = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length());
            Document doc = Jsoup.parse(file, "UTF-8", currentDirectory);
            ArrayList toReturn = new ArrayList();
            toReturn.add(doc);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static CSSStyleSheet getCss(String fileName){
        File file = new File("src/testFiles/" + fileName + ".css");
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
}
