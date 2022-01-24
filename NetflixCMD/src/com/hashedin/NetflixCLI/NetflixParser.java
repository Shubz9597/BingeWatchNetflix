package com.hashedin.NetflixCLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NetflixParser {

    public static void main(String args[]) {
        NetflixParseLogic movie = new NetflixParseLogic(Constants.CSV_FILEPATH);
        movie.showListDriver();
    }
}
