package com.acme.files;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ResourceFile extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_file);
        ArrayList<String> godos = new ArrayList<String>();
        try {
//obtenemos el recurso
            InputStream in = getResources().openRawResource(R.raw.godos);
            //generamos el documento XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in,null);
            NodeList fileGodos = doc.getElementsByTagName("godo");
// se recorren los nodos guardándolos en el array
            for (int i = 0; i < fileGodos.getLength(); i++){
                godos.add(((Element)fileGodos.item(i)).getTextContent());
            }
            in.close();
        }
        catch(Throwable t){
            Toast.makeText(this, "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
        }
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout. simple_list_item_1, godos));
    }
}
