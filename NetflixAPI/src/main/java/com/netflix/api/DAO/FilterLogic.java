package com.netflix.api.DAO;

import com.netflix.api.models.MovieModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilterLogic {

    public static List<ArrayList<String>> readCSV() {
        String CsvLineReader = "";
        ArrayList<ArrayList<String>> allMovieList = new ArrayList<>();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(Constants.CSV_FILEPATH));

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


    public static List<ArrayList<String>> listShowsBasedOnQuery(
            String count, String country, String listed_in, String startDate, String endDate
    ) {
        List<ArrayList<String>> showList = new ArrayList<>();
        List<ArrayList<String>> resultantList = new ArrayList<>();
        int userInput;
        userInput = (count != null)? Integer.parseInt(count): 0;
        showList = readCSV();
        if(userInput != 0) {
            resultantList = showList.stream()
                    .filter(show ->
                        show.get(Constants.SHOW_TYPE_COLUMN).toUpperCase().contains(Constants.TV_SHOW_STRING)
                    ).limit(userInput).collect(Collectors.toList());
        }

        if(country != null) {
            resultantList = showList.stream()
                    .filter(show -> show.get(Constants.SHOW_TYPE_COLUMN).toUpperCase().contains(Constants.TYPE_MOVIE_STRING) &&
                            show.get(Constants.COUNTRY_INDIA_COLUMN).toUpperCase().contains(Constants.COUNTRY_INDIA))
                    .limit(userInput).collect(Collectors.toList());

        }

        if(listed_in != null) {
            resultantList = showList.stream()
                    .filter(show ->
                            show.get(Constants.LISTED_IN_HORROR_COLUMN).toUpperCase().contains(Constants.HORROR_MOVIE_STRING)
                    ).limit(userInput).collect(Collectors.toList());
        }

        if(startDate != null && endDate != null) {
            DateFormat date = new SimpleDateFormat("dd/M/Y");
            DateFormat csvDate = new SimpleDateFormat("\"MMM d, y\"");
            try{
                Date initialParseDate = date.parse(startDate);
                Date finalParseDate = date.parse(endDate);

                resultantList = showList.stream()
                        .filter(show -> {
                            Date showDate = null;
                            try {
                                showDate = csvDate.parse(show.get(Constants.DATE_ADDED_COLUMN));
                            } catch (ParseException e) {
                                e.getMessage();
                            }

                            return (showDate != null && showDate.after(initialParseDate) && showDate.before(finalParseDate) &&
                                    show.get(Constants.SHOW_TYPE_COLUMN).toUpperCase().contains(Constants.TV_SHOW_STRING));
                            }).limit(userInput).collect(Collectors.toList());
            } catch (ParseException e) {
                e.getMessage();
            }



        }

        return resultantList;
    }

}
