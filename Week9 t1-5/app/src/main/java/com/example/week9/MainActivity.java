package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    EditText editTimeAft;
    EditText editTimeBef;
    TextView textView;
    Spinner spinnerPost;
    Spinner spinnerCountry;
    Spinner spinnerDay;
    PostSuper postSuper = PostSuper.getInstance();
    int choice;
    String countryChoice;
    Date open;
    Date close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        this.readXMLFIN();
        this.readXMLEST();
        this.spinnerCountry();
        this.daySpinner();


    }


    public void search(View v) {
        editTimeBef = findViewById(R.id.editTimeBef);
        editTimeAft = findViewById(R.id.editTimeAft);

        String openTime = editTimeAft.getText().toString();
        String closingTime = editTimeBef.getText().toString();

        open = postSuper.dateFormats(openTime);
        close = postSuper.dateFormats(closingTime);


        if (countryChoice.equalsIgnoreCase("Finland")) {
            postSuper.createmodifiedlistFI(choice, open, close);
            spinnerPostFI();
        } else if (countryChoice.equalsIgnoreCase("Estonia")) {
            postSuper.createmodifiedlistEE(choice, open, close);
            spinnerPostEE();
        } else {
            postSuper.createmodifiedlistBoth(choice, open, close);
            spinnerPostAll();
        }

    }

    public void getPostinfo(Post post) {
        String info = post.getName();
        info += "\n" + post.getCity() + "\n" + post.getAddress() + "\n";
        info += post.getPostalcode() + "\n" + post.getAvailability();
        setTextView(info);
    }

    public void setTextView(String s) {
        textView = findViewById(R.id.textView);
        textView.setText(s);

    }

    public void daySpinner() {
        spinnerDay = findViewById(R.id.spinnerDay);

        final ArrayList<String> dayList = new ArrayList<String>();

        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
        dayList.add("Sunday");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter);
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void spinnerPostFI() {
        final Post[] selectedpost = new Post[1];
        spinnerPost = findViewById(R.id.spinnerPost);
        final ArrayList<Post> list = postSuper.getModifiedlistfin();

        ArrayAdapter<Post> adapterPost =
                new ArrayAdapter<Post>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapterPost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPost.setAdapter(adapterPost);

        spinnerPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                //check if spinner2 has a selected item and show the value in edittext

                getPostinfo((Post) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
    }

    public void spinnerPostEE() {
        final Post[] selectedpost = new Post[1];
        spinnerPost = findViewById(R.id.spinnerPost);
        final ArrayList<Post> list = postSuper.getModifiedlistest();

        ArrayAdapter<Post> adapterPost =
                new ArrayAdapter<Post>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapterPost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPost.setAdapter(adapterPost);

        spinnerPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                getPostinfo((Post) parent.getItemAtPosition(position));
                //check if spinner2 has a selected item and show the value in edittext

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
    }

    public void spinnerPostAll() {
        final Post[] selectedpost = new Post[1];
        spinnerPost = findViewById(R.id.spinnerPost);
        final ArrayList<Post> list = postSuper.getModifiedlistboth();

        ArrayAdapter<Post> adapterPost =
                new ArrayAdapter<Post>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapterPost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPost.setAdapter(adapterPost);

        spinnerPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                getPostinfo((Post) parent.getItemAtPosition(position));
                //check if spinner2 has a selected item and show the value in edittext

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
    }

    public void spinnerCountry() {
        spinnerCountry = findViewById(R.id.spinnerCountry);
        ArrayList<String> countryList = new ArrayList<String>();
        countryList.add("Both");
        countryList.add("Finland");
        countryList.add("Estonia");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(adapter);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Option Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                countryChoice = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });

    }

    public void readXMLFIN() {
        String name;
        String addr;
        String city;
        String availability;
        String postal;


        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            String urlString = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    name = element.getElementsByTagName("name").item(0).getTextContent();
                    addr = element.getElementsByTagName("address").item(0).getTextContent();
                    city = element.getElementsByTagName("city").item(0).getTextContent();
                    availability = element.getElementsByTagName("availability").item(0).getTextContent();
                    postal = element.getElementsByTagName("postalcode").item(0).getTextContent();
                    Post newPost = new Post(name, addr, "FI", availability, city, postal);
                    postSuper.availabilityToDateFI(newPost);
                    postSuper.addToFIList(newPost);
                }


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            System.out.println("!!!!!!!!!!!!!DONE !!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    public void readXMLEST() {
        String name;
        String addr;
        String city;
        String availability;
        String postal;


        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            String urlString = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=EE&type=APT";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    name = element.getElementsByTagName("name").item(0).getTextContent();
                    addr = element.getElementsByTagName("address").item(0).getTextContent();
                    city = element.getElementsByTagName("city").item(0).getTextContent();
                    availability = element.getElementsByTagName("availability").item(0).getTextContent();
                    postal = element.getElementsByTagName("postalcode").item(0).getTextContent();
                    Post newPost = new Post(name, addr, "EE", postSuper.normalizeAvailabilityEE(availability), city, postal);
                    postSuper.availabilityToDateEE(newPost);
                    postSuper.addToEEList(newPost);
                }


            }

        } catch (ParserConfigurationException | ParseException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("!!!!!!!!!!!!!DONE !!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

}
