package api;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CepApiConsumer {

    public String getenderenco(String cep) throws IOException, InterruptedException {
        var url = "http://viacep.com.br/ws/".concat(cep).concat("/json/");

       var request = HttpRequest.newBuilder()
                .GET().uri(URI.create(url))
               .build();


       var httpClient = HttpClient.newBuilder()
               .connectTimeout(Duration.ofSeconds(5))
               .build();

       var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        var gson = new Gson();

        var Enderenco = gson.fromJson(response.body(), Model.Enderenco.class);

        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xmlString = objectMapper.writeValueAsString(Enderenco);

        return xmlString;
    }
}
