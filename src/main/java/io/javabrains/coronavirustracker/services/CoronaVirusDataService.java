package io.javabrains.coronavirustracker.services;

import jakarta.annotation.PostConstruct;
import io.javabrains.coronavirustracker.models.StatisticeTari;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    public List<StatisticeTari> getAllStats() {
        return allStats;
    }

    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
private List<StatisticeTari> allStats=new ArrayList<>();
@PostConstruct
@Scheduled(cron ="* 1 1 * * *")
    public void aduceVirusData() throws IOException, InterruptedException {
    List<StatisticeTari> newsStats=new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());


    StringReader csvBodyReader= new StringReader(httpResponse.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    for (CSVRecord record : records) {
        StatisticeTari locationStat=new StatisticeTari();

       locationStat.setJudet(record.get("Province/State"));
       locationStat.setTara(record.get("Country/Region"));
        int upOnDayCases = Integer.parseInt(record.get(record.size() - 1));
        int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
        locationStat.setLatestTotalCazuri(upOnDayCases);
        locationStat.setDifFromPrevDay(upOnDayCases-prevDayCases);
        newsStats.add(locationStat);


    }
this.allStats=newsStats;
}
}
