package com.netflix.api.controller;

import com.netflix.api.DAO.FilterLogic;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BaseController {

    @GetMapping("/tvshows")
    public ResponseEntity<List<ArrayList<String>>> getTVShow(
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "listed_in", required = false) String listed_in,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {


     long responseStartTimer = System.currentTimeMillis();
     HttpHeaders responseHeader = new HttpHeaders();
     List<ArrayList<String>> showList;
     showList = FilterLogic.listShowsBasedOnQuery(count, country, listed_in, startDate, endDate);
     long responseEndTimer = System.currentTimeMillis() - responseStartTimer;
     responseHeader.set("X-TIME-TO-EXECUTE", String.format("%s ms", responseEndTimer));
     return ResponseEntity.ok().headers(responseHeader).body(showList);
    }

}
