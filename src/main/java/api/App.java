package api;

import javax.swing.*;
import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;


public class App {
    public static void main(String[] agrs) throws IOException, InterruptedException {

        var cepConsumer = new CepApiConsumer();

        var cep = JOptionPane.showInputDialog("Digite o cep: ");

        var enderenco = cepConsumer.getenderenco(cep);


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(enderenco));
            Document document = builder.parse(is);

            String cidade = document.getElementsByTagName("localidade").item(0).getTextContent();
            String uf = document.getElementsByTagName("uf").item(0).getTextContent();
            String gia = document.getElementsByTagName("gia").item(0).getTextContent();
            String bairro = document.getElementsByTagName("bairro").item(0).getTextContent();
            String complemento = document.getElementsByTagName("complemento").item(0).getTextContent();
            String logradouro = document.getElementsByTagName("logradouro").item(0).getTextContent();
            String cepRetornado = document.getElementsByTagName("cep").item(0).getTextContent();

            StringBuilder enderecoBuilder = new StringBuilder();
            enderecoBuilder.append("Endereço: \n")
                    .append("Cidade: ").append(cidade).append("\n")
                    .append("UF: ").append(uf).append("\n")
                    .append("GIA: ").append(gia).append("\n")
                    .append("Bairro: ").append(bairro).append("\n")
                    .append("Complemento: ").append(complemento).append("\n")
                    .append("Logradouro: ").append(logradouro).append("\n")
                    .append("CEP: ").append(cepRetornado).append("\n");

            JOptionPane.showMessageDialog(null, enderecoBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
