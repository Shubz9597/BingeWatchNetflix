package com.hashedin.NetflixCLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NetflixParser {

    public static void main(String args[]) {
        NetflixParseLogic movie = new NetflixParseLogic("E:\\Hashedin\\netflix_titles.csv");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Length");
        int userInput = sc.nextInt();
        List<ArrayList<String>> pepe = movie.listTVShows(userInput);
        System.out.println(pepe);
        List<ArrayList<String>> horrorPepe = movie.genreHorror(userInput);
        System.out.println(horrorPepe);
        List<ArrayList<String>> indianPepe = movie.countryInd(userInput);
        System.out.println(indianPepe);
        sc.close();
    }
}
