package com.company;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;



public class ParserFinviz {
    public static String name = "row";
    public static String[] rowresult;

    static int number;


    public static void main(String[] args) throws IOException {

        parserChartmill.ParsNumberPage();
        System.out.println("Страниц парсим: " + number);

        parserChartmill.ParsFirstPage();

      parserChartmill.Pars();

    }
    public static ParserFinviz parserChartmill = new ParserFinviz();

    public static Elements results;
    public static String tickerData;
    public static String[] resultsFinal;
    private String url = "https://finviz.com/screener.ashx?v=141&f=sh_avgvol_o100,sh_price_u15,sh_relvol_o1&ft=4";
    //Main.globalUrl;


    private static Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 5000);
        return page;
    }

    public void ParsFirstPage() throws IOException  {
        Document page = getPage(url);
        Elements table = page.select("table [width=100%]");
        results = table.select("tr [class=screener-body-table-nw]");
        Elements elements = results.select("[class=screener-link-primary]");

        PrintWriter writer = new PrintWriter("TICKER.txt", "UTF-8");
        rowresult = elements.text().split(" ");
        System.out.println(rowresult.length);

        writer.println(elements.text());

        writer.close();


       // System.out.println(elements.text());
    }

    public void Pars() throws IOException  {
            String url2 = "&r=";
            int url3 = 21;

            for (int i = 1; i < number ; i++) {

            Document page2 = getPage(url + url2 + url3);

            Elements table = page2.select("table [width=100%]");

            results = table.select("tr [class=screener-body-table-nw]");
            Elements elements = results.select("[class=screener-link-primary]");
            tickerData = elements.text();
            System.out.println(tickerData);

                try {
                    PrintWriter prWr = new PrintWriter(new BufferedWriter(new FileWriter("TICKER.txt", true)));
                    prWr.println(tickerData);
                    prWr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            url3 = url3 + 20;
        }
    }
    public void ParsNumberPage() throws IOException {
        Document page2 = getPage(url);
        Elements numberpage = page2.select("a");
        Elements num = numberpage.select("[class=screener-pages]");

        String[] number1 = num.text().split(" ");

        String number2 = number1[number1.length -1];
        number = Integer.parseInt(number2);

    }

}
