package com.example.week9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostSuper {
    private static ArrayList<Post> postlistest = new ArrayList<Post>();
    private static ArrayList<Post> postlistfin = new ArrayList<Post>();
    private static ArrayList<Post> postlistall = new ArrayList<Post>();

    private static ArrayList<Post> modifiedlistfin = new ArrayList<Post>();

    private static ArrayList<Post> modifiedlistest = new ArrayList<Post>();

    public static ArrayList<Post> getModifiedlistboth() {
        return modifiedlistboth;
    }

    public static void setModifiedlistboth(ArrayList<Post> modifiedlistboth) {
        PostSuper.modifiedlistboth = modifiedlistboth;
    }

    private static ArrayList<Post> modifiedlistboth = new ArrayList<Post>();




    //GETSETS
    public static ArrayList<Post> getPostlistall() {
        return postlistall;
    }

    public static ArrayList<Post> getModifiedlistest() {
        return modifiedlistest;
    }

    public static void setModifiedlistest(ArrayList<Post> modifiedlistest) {
        PostSuper.modifiedlistest = modifiedlistest;
    }

    public static ArrayList<Post> getModifiedlistfin() {
        return modifiedlistfin;
    }

    public static void setModifiedlistfin(ArrayList<Post> modifiedlistfin) {
        PostSuper.modifiedlistfin = modifiedlistfin;
    }

    public void setPostlistall(ArrayList<Post> postlistall) {
        this.postlistall = postlistall;
    }
    public static ArrayList<Post> getPostlistfin() {
        return postlistfin;
    }
    public void setPostlistfin(ArrayList<Post> postlistfin) {
        this.postlistfin = postlistfin;
    }
    public static ArrayList<Post> getPostlistest() {
        return postlistest;
    }
    public void setPostlistest(ArrayList<Post> postlistest) {
        this.postlistest = postlistest;
    }




    //SINGLETON
    private static PostSuper instance = new PostSuper();
    public static PostSuper getInstance() {
        return instance;
    }

    //Constructor
    private PostSuper() {
    }

    //method for creating list of finnish posts
    public void addToFIList(Post newPost) {
        ArrayList<Post> list = getPostlistfin();
        ArrayList<Post> listall = getPostlistall();
        list.add(newPost);
        listall.add(newPost);
        setPostlistfin(list);
        setPostlistall(listall);
    }

    //method for creating list of estonian posts
    public void addToEEList(Post newPost) {
        ArrayList<Post> listall = getPostlistall();
        ArrayList<Post> list = getPostlistest();
        listall.add(newPost);
        list.add(newPost);
        setPostlistall(listall);
        setPostlistest(list);
    }

    //method for normalizing availability for estonian posts
    public String normalizeAvailabilityEE(String availability) {
        availability = availability.replace("24h", "00.00-24.00");
        availability = availability.replace(" - ", "-");
        availability = availability.replace("kell", "");
        availability = availability.replace(" L-P-", "");
        availability = availability.replace("00 2", "00-2");
        availability = availability.replace("00 -2", "00-2");
        availability = availability.replace("9:00 – 18:00", "9:00–18:00");
        availability = availability.replace("E-P  9", "E-P 9");
        availability = availability.replace("E-P  0", "E-P 0");
        availability = availability.replace("E-N, P 8.00-22.00, R-L 8.00-22.00", "E-P 08:00-22:00");
        return availability;
    }

    //method for parsing finnish post availability string to date format
    public void availabilityToDateFI(Post post) {
        Date[][] openinghours = post.getOpeningdates();
        String ava = post.getAvailability();
        ava.replace(" - ", "-");
        String[] parts = ava.split("[, ]+");

        if (parts.length == 1) {
            for (int j = 0; j<7;j++) { //hours for mon - sun
                openinghours[j][0] = dateFormats("00:00");
                openinghours[j][1] = dateFormats("24:00");
            }
        }
        //if days are mon-su
        if (parts.length==4){
            if (parts[0].equalsIgnoreCase("ma-su")) {
                for (int j = 0; j<7;j++) { //hours for mon - sun
                    openinghours[j][0] = dateFormats(parts[1]);
                    openinghours[j][1] = dateFormats(parts[3]);
                }
            } else if (parts[0].equalsIgnoreCase("ma-pe")) {
                for (int j = 0; j<5;j++) { //hours for mon - fri
                    openinghours[j][0] = dateFormats(parts[1]);
                    openinghours[j][1] = dateFormats(parts[3]);
                }

            } else if (parts[0].equalsIgnoreCase("ma-la")) {
                for (int j = 0; j<6;j++) { //hours for mon - sat
                    openinghours[j][0] = dateFormats(parts[1]);
                    openinghours[j][1] = dateFormats(parts[3]);
                }
            }

        }

        //if days are mon-fri, sat and sun
        if (parts.length==12) {
            for (int j = 0; j<5;j++) { //hours for mon - fri
                openinghours[j][0] = dateFormats(parts[1]);
                openinghours[j][1] = dateFormats(parts[3]);
            }
            openinghours[5][0] = dateFormats(parts[5]); //saturday
            openinghours[5][1] = dateFormats(parts[7]); //saturday
            openinghours[6][0] = dateFormats(parts[9]); //sunday
            openinghours[6][1] = dateFormats(parts[11]); //sunday
        }

        if (parts.length==8) {
            if (parts[0].equalsIgnoreCase("ma-pe")) {
                for (int j = 0; j < 5; j++) { //hours for mon - fri
                    openinghours[j][0] = dateFormats(parts[1]);
                    openinghours[j][1] = dateFormats(parts[3]);
                }
                if (parts[4].equalsIgnoreCase("la")) {
                    openinghours[5][0] = dateFormats(parts[5]);
                    openinghours[5][1] = dateFormats(parts[7]);
                } else if (parts[4].equalsIgnoreCase("la-su")) {
                    openinghours[5][0] = dateFormats(parts[5]);
                    openinghours[5][1] = dateFormats(parts[7]);
                    openinghours[6][0] = dateFormats(parts[5]);
                    openinghours[6][1] = dateFormats(parts[7]);
                }
            }
            if (parts[0].equalsIgnoreCase("ma-la")) {
                for (int j = 0; j < 6; j++) { //hours for mon - fri
                    openinghours[j][0] = dateFormats(parts[1]);
                    openinghours[j][1] = dateFormats(parts[3]);
                }
                if (parts[4].equalsIgnoreCase("su")) {
                    openinghours[6][0] = dateFormats(parts[5]);
                    openinghours[6][1] = dateFormats(parts[7]);
                }
            }
        }

        post.setOpeningdates(openinghours);
    }



    //method for parsing estonian availability string to date format
    public void availabilityToDateEE(Post posti) {
        String ava = posti.getAvailability();
        String[] parts = ava.split("[, ;]+");
        Date[][] openinghours = posti.getOpeningdates();

        if (parts.length == 2) {
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equalsIgnoreCase("E-L")) {

                    String[] parts2 = parts[i+1].split("[- –]+");

                    for (int j = 0; j<6;j++) {
                        openinghours[j][0] = dateFormats(parts2[0]);
                        openinghours[j][1] = dateFormats(parts2[1]);
                    }


                } if (parts[i].equalsIgnoreCase("E-P")) {


                    String[] parts2 = parts[i+1].split("[- –]+");
                    for (int j = 0; j<7;j++) {
                        openinghours[j][0] = dateFormats(parts2[0]);
                        openinghours[j][1] = dateFormats(parts2[1]);
                    }
                }  if (parts[i].equalsIgnoreCase("P")) {
                    String[] parts2 = parts[i+1].split("[- –]+");
                    openinghours[6][0] = dateFormats(parts2[0]);
                    openinghours[6][1] = dateFormats(parts2[1]);
                }  if (parts[i].equalsIgnoreCase("E-R")) {
                    String[] parts2 = parts[i+1].split("[- –]+");
                    for (int j = 0; j<5;j++) {
                        openinghours[j][0] = dateFormats(parts2[0]);
                        openinghours[j][1] = dateFormats(parts2[1]);
                    }

                }

            }

        } else if (parts.length == 4) {
            for (int i = 0; i < parts.length; i++) {
                //if clause for mon-sat + sun opening hours
                if (parts[i].equalsIgnoreCase("E-L")) {

                    String[] parts2 = parts[i + 1].split("[- –]+");


                    for (int j = 0; j < 6; j++) {
                        openinghours[j][0] = dateFormats(parts2[0]);
                        openinghours[j][1] = dateFormats(parts2[1]);
                    }
                    if (parts[i + 2].equalsIgnoreCase("P")) {
                        String[] parts3 = parts[i + 3].split("[- –]+");
                        openinghours[6][0] = dateFormats(parts3[0]);
                        openinghours[6][1] = dateFormats(parts3[1]);
                    }


                }
                //if clause for mon-fri + sat-sun hours
                if (parts[i].equalsIgnoreCase("E-R")) {
                    String[] parts2 = parts[i + 1].split("[- –]+");
                    for (int j = 0; j < 5; j++) {
                        openinghours[j][0] = dateFormats(parts2[0]);
                        openinghours[j][1] = dateFormats(parts2[1]);
                    }

                    if (parts[i + 2].equalsIgnoreCase("L-P")) {
                        String[] parts3 = parts[i + 3].split("[- –]+");
                        openinghours[5][0] = dateFormats(parts3[0]);
                        openinghours[5][1] = dateFormats(parts3[1]);
                        openinghours[6][0] = dateFormats(parts3[0]);
                        openinghours[6][1] = dateFormats(parts3[1]);

                    }

                }



                }

            }
        posti.setOpeningdates(openinghours);
        }



       /* System.out.println(posti.getName()+"  availability for this post:  ");
        for (int i = 0;i<7;i++) {
            if( i==0) {

                System.out.println("MAANANTAINA AUKEAA:" + openinghours[i][0]);
                System.out.println("SULKEUTUU" + openinghours[i][1]);
            }
            if( i==1) {

                System.out.println("TIISTAINA AUKEAA:" + openinghours[i][0]);
                System.out.println("SULKEUTUU" + openinghours[i][1]);
            }
            if( i==2) {

                System.out.println("KESKIVIIKKONA AUKEAA:" + openinghours[i][0]);
                System.out.println("SULKEUTUU" + openinghours[i][1]);
            }
            if( i==3) {

                System.out.println("TORSTAINA AUKEAA:" + openinghours[i][0]);
                System.out.println("SULKEUTUU" + openinghours[i][1]);
            }
            if( i==4) {

                System.out.println("PERJANTAINA AUKEAA:" + openinghours[i][0]);
                System.out.println("SULKEUTUU" + openinghours[i][1]);
            }
            if( i==5) {

                System.out.println("LAUANTAINA AUKEAA:  " + openinghours[i][0]);
                System.out.println("SULKEUTUU  " + openinghours[i][1]);
            }
            if( i==6) {

                System.out.println("SUNNUNTAINA AUKEAA:  " + openinghours[i][0]);
                System.out.println("SULKEUTUU  " + openinghours[i][1]);
            }


        }*/



    //method for different timeformats
    public Date dateFormats(String testdate) {
        List<SimpleDateFormat> patterns = new ArrayList<SimpleDateFormat>();
        patterns.add(new SimpleDateFormat("HH:mm"));
        patterns.add(new SimpleDateFormat("H:mm"));
        patterns.add(new SimpleDateFormat("HH.mm"));
        patterns.add(new SimpleDateFormat("H.mm"));
        patterns.add(new SimpleDateFormat("H"));
        patterns.add(new SimpleDateFormat("HH"));

        for (SimpleDateFormat pattern : patterns) {
            try {
                return new Date(pattern.parse(testdate).getTime());
            } catch (ParseException pe) {
            }

        }
        System.out.println("String: " + testdate +"DIDNT MATCH ANY KNOWN DATEFORMATS");
        return null;
    }

    public void createmodifiedlistFI (int day, Date open, Date close) {
        ArrayList<Post> list = getPostlistfin();
        ArrayList<Post> newlist = new ArrayList<Post>();

        for (Post post : list) {
            Date[][] hours = post.getOpeningdates();
            if ((hours[day][0]) != null) {

                if (hours[day][0].before(open) && hours[day][1].after(close)){
                    newlist.add(post);
                }
                }
        }
        setModifiedlistfin(newlist);
    }

    public void createmodifiedlistBoth (int day, Date open, Date close) {
        ArrayList<Post> list = getPostlistall();
        ArrayList<Post> newlist = new ArrayList<Post>();

        for (Post post : list) {
            Date[][] hours = post.getOpeningdates();
            if ((hours[day][0]) != null) {
                if (hours[day][0].before(open) && hours[day][1].after(close)){
                    newlist.add(post);
                    System.out.println("addattiin koska" + hours[day][0] + "on before" + open);
                }

            }
        }
        setModifiedlistboth(newlist);
    }

    public void createmodifiedlistEE (int day, Date open, Date close) {
        ArrayList<Post> list = getPostlistest();
        ArrayList<Post> newlist = new ArrayList<Post>();

        for (Post post : list) {
            Date[][] hours = post.getOpeningdates();
            if ((hours[day][0]) != null) {
                if (hours[day][0].before(open) && hours[day][1].after(close)){
                    newlist.add(post);
                }

            }
        }
        setModifiedlistest(newlist);
    }

}
