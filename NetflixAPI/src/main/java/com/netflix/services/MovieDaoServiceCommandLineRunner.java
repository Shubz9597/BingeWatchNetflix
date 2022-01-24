package com.netflix.services;

import com.netflix.api.DAO.Constants;
import com.netflix.api.DAO.MovieDAOService;
import com.netflix.api.models.MovieModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDaoServiceCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MovieDaoServiceCommandLineRunner.class);

    @Autowired
    private MovieDAOService movieDAOService;

    @Override
    public void run(String ...arg0)throws Exception {

        String CsvLineReader = "";
        List<ArrayList<String>> allMovieList = new ArrayList<>();

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(Constants.CSV_FILEPATH));

            while ((CsvLineReader = csvReader.readLine()) != null) {
                String[] movieDetail = CsvLineReader.split(Constants.REGEX_SPLIT_CSV);
                MovieModel movieModel = new MovieModel(movieDetail[0], movieDetail[1], movieDetail[2], movieDetail[3], movieDetail[4], movieDetail[5],
                        movieDetail[6], movieDetail[7], movieDetail[8], movieDetail[9], movieDetail[10], movieDetail[11]);

                long insert = movieDAOService.insert(movieModel);
                log.info("Movie Added id "+insert);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}