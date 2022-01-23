package com.hashedin.NetflixCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public List<ArrayList<String>> listTVShows(int userInput) {
        List<ArrayList<String>> tvShows= new ArrayList<>();
        System.out.println("All the types of Shows with type as TV Show");
        tvShows = allShows.stream()
                .filter(show -> show.get(1).toUpperCase().contains(Constants.TV_SHOW_STRING))
                .limit(userInput).collect(Collectors.toList());

        return tvShows;
    }


    public List<ArrayList<String>> genreHorror(int userInput) {
        List<ArrayList<String>> horrorShows= new ArrayList<>();
        System.out.println("All the types of Shows with Genre as Horror");
        horrorShows = allShows.stream()
                .filter(show -> show.get(10).toUpperCase().contains(Constants.HORROR_MOVIE_STRING))
                .limit(userInput).collect(Collectors.toList());

        return horrorShows;
    }

    public List<ArrayList<String>> countryInd(int userInput) {
        List<ArrayList<String>> countrySpecific= new ArrayList<>();
        System.out.println("All the types of Shows with Genre as Horror");
        countrySpecific = allShows.stream()
                .filter(show -> show.get(5).toUpperCase().contains(Constants.COUNTRY_INDIA))
                .limit(userInput).collect(Collectors.toList());

        return countrySpecific;
    }
}
