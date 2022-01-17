package workspace.home.sampler.writing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class xmlWriter implements Writable {
    @Override
    public void write(String destFolder, List<HashMap<String, String>> recordStream) throws IOException {
        Document dom;
        Element e1;
        Element e2;
        String ext = ".xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element rootEle = dom.createElement("labTests");

            for (HashMap<String, String> record: recordStream)
            {
                e1 = dom.createElement("labTest");
                for (String key: record.keySet())
                {
                    e2 = dom.createElement(key);
                    e2.appendChild(dom.createTextNode(record.get(key)));
                    e1.appendChild(e2);
                }
                rootEle.appendChild(e1);
            }

            dom.appendChild(rootEle);
            writeToXml(destFolder + ext, dom);
        }
        catch (ParserConfigurationException pce)
        {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    private void writeToXml(String dest, Document dom)
    {
        try
        {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            tr.transform(new DOMSource(dom),
                    new StreamResult(new FileOutputStream(dest)));

        }
        catch (TransformerException | IOException te)
        {
            System.out.println(te.getMessage());
        }
    }
}
