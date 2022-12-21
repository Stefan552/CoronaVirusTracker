package io.javabrains.coronavirustracker.controller;

import io.javabrains.coronavirustracker.models.StatisticeTari;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomePaginaController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping ("/")
    public String home (Model model){
        List<StatisticeTari> allStats = coronaVirusDataService.getAllStats();
        int sumAllCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCazuri()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",sumAllCases);
        return "home";
    }
}
