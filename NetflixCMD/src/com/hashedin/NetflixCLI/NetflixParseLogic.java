package com.hashedin.NetflixCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class NetflixParseLogic {
    private ArrayList<ArrayList<String>> allShows;

    public NetflixParseLogic(String filePath) {
        this.allShows = readCSV(filePath);
    }
    private ArrayList<ArrayList<String>> readCSV(String filePath)  {

        String CsvLineReader = "";
        ArrayList<ArrayList<String>> allMovieList = new ArrayList<>();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

            while((CsvLineReader = csvReader.readLine()) != null) {
                String[] movieDetail = CsvLineReader.split(Constants.REGEX_SPLIT_CSV);
                List<String> listMovie = Arrays.asList(movieDetail);
                ArrayList<String> currentMovie = new ArrayList<String>(listMovie);
                allMovieList.add(currentMovie);
            }
        }

        catch(IOException e) {
            e.getMessage();
        }

        return allMovieList;

    }

    public void showListDriver() {

        Scanner sc = new Scanner(System.in);
        List<ArrayList<String>> finalResultList = new ArrayList<>();
        System.out.println("Enter the Length");
        int userInput = sc.nextInt();
        System.out.println("Enter the choice: \n" +
                "1) Show Records where Type = TV Show \n" +
                "2) Show Records where Genre = Horror Movies \n" +
                "3) Show Records where Type = Movie and Country = India \n" );

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                finalResultList = listShowsBasedOnQuery(
                        userInput,
                        Constants.SHOW_TYPE_COLUMN,
                        Constants.TV_SHOW_STRING
                );
                break;
            case 2:
                finalResultList = listShowsBasedOnQuery(
                        userInput,
                        Constants.LISTED_IN_HORROR_COLUMN,
                        Constants.HORROR_MOVIE_STRING
                );
                break;
            case 3:
                finalResultList = listShowsBasedOnQuery(
                        userInput,
                        Constants.COUNTRY_INDIA_COLUMN,
                        Constants.SHOW_TYPE_COLUMN,
                        Constants.COUNTRY_INDIA,
                        Constants.TYPE_MOVIE_STRING
                );
                break;
        }
        System.out.println(finalResultList);
        sc.close();
    }


    public List<ArrayList<String>> listShowsBasedOnQuery(int userInput, int columnNumber, String query) {

        List<ArrayList<String>> showList= new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Do You want to provide Start and End Date Y or N");
        String choiceForDate = sc.next();

        switch (choiceForDate) {
            case "Y":
                showList = showsBetweenDates(allShows, userInput, columnNumber, query);
                break;

            case "N":
                showList = allShows.stream()
                        .filter(show -> show.get(columnNumber).toUpperCase().contains(query))
                        .limit(userInput).collect(Collectors.toList());
                break;

        }
        return showList;
    }

    public List<ArrayList<String>> listShowsBasedOnQuery(
            int userInput, int columnNumber, int columnNumber2, String query1, String query2) {

        List<ArrayList<String>> showList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Do You want to provide Start and End Date Y or N");

        String choiceForDate = sc.next();

        switch (choiceForDate) {
            case "Y":
                showList = showsBetweenDates(allShows, userInput, columnNumber, columnNumber2, query1, query2);
                break;

            case "N":
                showList = allShows.stream()
                        .filter(show -> show.get(columnNumber).toUpperCase().contains(query1) &&
                                show.get(columnNumber2).toUpperCase().contains(query2))
                        .limit(userInput).collect(Collectors.toList());
                break;



        }
        return showList;
    }

        public List<ArrayList<String>> showsBetweenDates(
                List < ArrayList < String >> showList,
                int userInput,
                int columnNumber,
                String query){
            Scanner sc = new Scanner(System.in);
            List<ArrayList<String>> dateBetweenShows = new ArrayList<>();
            DateFormat date = new SimpleDateFormat("\"MMM d, y\"");

            try {
                System.out.println("Enter the Initial Month name, For Example \"December\"");
                String initialMonth = sc.next();
                System.out.println("Enter the Initial Date, For Example \"25\"");
                String initialDay = sc.next();
                System.out.println("Enter the Initial Year, For Example \"2005\"");
                String initialYear = sc.next();

                String initialDate = "\"" +initialMonth + " " + initialDay + ", " + initialYear + "\"";
                Date initialParseDate = date.parse(initialDate);

                System.out.println("Enter the Final Month name, For Example \"December\"");
                String endMonth = sc.next();
                System.out.println("Enter the Final Date, For Example \"25\"");
                String endDay = sc.next();
                System.out.println("Enter the Final Year, For Example \"2005\"");
                String endYear = sc.next();

                String finalDate = "\"" + endMonth + " " + endDay + ", " + endYear + "\"";
                Date finalParseDate = date.parse(finalDate);
                dateBetweenShows = showList.stream()
                        .filter(show -> {
                            Date showDate = null;
                            try {
                                showDate = date.parse(show.get(Constants.DATE_ADDED_COLUMN));
                                //System.out.println(showDate);
                            } catch (ParseException e) {
                                e.getMessage();
                            }
                            return (showDate != null && showDate.after(initialParseDate) && showDate.before(finalParseDate) &&
                                    show.get(columnNumber).toUpperCase().contains(query));
                        }).limit(userInput).collect(Collectors.toList());


            } catch (ParseException e) {
                e.getMessage();
            }

            sc.close();
            //System.out.println(dateBetweenShows);
            return dateBetweenShows;
        }

        public List<ArrayList<String>> showsBetweenDates (
                List < ArrayList < String >> showList,
                int userInput,
                int columnNumber,
                int columnNumber2,
                String query1,
                String query2
        ){
            Scanner sc = new Scanner(System.in);
            List<ArrayList<String>> dateBetweenShows = new ArrayList<>();
            DateFormat date = new SimpleDateFormat("\"MMM d, y\"");

            try {
                System.out.println("Enter the Initial Month name, For Example \"December\"");
                String initialMonth = sc.next();
                System.out.println("Enter the Initial Date, For Example \"25\"");
                String initialDay = sc.next();
                System.out.println("Enter the Initial Year, For Example \"2005\"");
                String initialYear = sc.next();

                String initialDate =  "\"" + initialMonth + " " + initialDay + ", " + initialYear + "\"";
                Date initialParseDate = date.parse(initialDate);

                System.out.println("Enter the Final Month name, For Example \"December\"");
                String endMonth = sc.next();
                System.out.println("Enter the Final Date, For Example \"25\"");
                String endDay = sc.next();
                System.out.println("Enter the Final Year, For Example \"2005\"");
                String endYear = sc.next();

                String finalDate = "\"" + endMonth + " " + endDay + ", " + endYear + "\"";
                Date finalParseDate = date.parse(finalDate);


                dateBetweenShows = showList.stream()
                        .filter(show -> {
                            Date showDate = null;
                            try {
                                showDate = date.parse(show.get(Constants.DATE_ADDED_COLUMN));
                            } catch (ParseException e) {
                                e.getMessage();
                            }
                            return (showDate != null && showDate.after(initialParseDate) && showDate.before(finalParseDate) &&
                                    show.get(columnNumber).toUpperCase().contains(query1) &&
                                    show.get(columnNumber2).toUpperCase().contains(query2));
                        }).limit(userInput).collect(Collectors.toList());


            } catch (ParseException e) {
                e.getMessage();
            }

            sc.close();
            return dateBetweenShows;
        }

}
