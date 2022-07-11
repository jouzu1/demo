package com.example.demo.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/test")
    public String welcome() {
        return "Welcome to the club baby!";
    }

    /**
     * @return
     * @throws IOException
     */
    @GetMapping("/scrap")
    public static void scrap() throws IOException {
        List<String[]> productList = new ArrayList<>();
        List<String> linkedList = new ArrayList<>();
        int page = 0;

        String titleText = "";
        String priceText = "";
        String rateText = "";
        String storeText = "";

        for (int i = 1; i < 10; i++) {
            String webPage = "https://www.tokopedia.com/p/handphone-tablet/handphone?page=1&rt=4,5";
            webPage = i == 1 ? webPage : "https://www.tokopedia.com/p/handphone-tablet/handphone?page=" + i + "&rt=4,5";

            Document document = Jsoup.connect(webPage).get();
            Elements titles = document.getElementsByClass("css-1bjwylw");
            Elements prices = document.getElementsByClass("css-o5uqvq");
            Elements ratingsElements = document.getElementsByClass("css-t70v7i");
            Elements merchantStores = document.getElementsByClass("css-1kr22w3");

            Elements linkProducts = document.getElementsByClass("css-89jnbj");
            // System.out.println(linkProducts.text());
            for(Element linkProduct : linkProducts){
                String linkProductDetail = linkProduct.attr("abs:href");
                System.out.println(linkProductDetail);
                Document productDetail = Jsoup.connect(linkProductDetail).userAgent("Opera").timeout(0).get();

            }

            for (Element title : titles) {
                titleText = title.text();
                for (Element price : prices) {
                    priceText = price.text();
                    for (Element ratingsElement : ratingsElements) {
                        rateText = ratingsElement.text();
                    }
                }
            }
            for (Element merchantStore : merchantStores) {
                storeText = merchantStore.text();
            }
            productList.add(new String[] { titleText, priceText, rateText, storeText });
            page = i;
        }

        if (productList.size() < 100) {
            while (productList.size() < 100) {
                String webPage = "https://www.tokopedia.com/p/handphone-tablet/handphone?page=" + page++ + "&rt=4,5";

                Document document = Jsoup.connect(webPage).get();
                Elements titles = document.getElementsByClass("css-1bjwylw");
                Elements prices = document.getElementsByClass("css-o5uqvq");
                Elements ratingsElements = document.getElementsByClass("css-t70v7i");
                Elements merchantStores = document.getElementsByClass("css-1kr22w3");

                Elements linkProducts = document.getElementsByClass("css-89jnbj");
                for(Element linkProduct : linkProducts){
                    String linkProductDetail = linkProduct.attr("abs:href");
                    // System.out.println(linkProductDetail);

                }

                for (Element title : titles) {
                    titleText = title.text();
                    for (Element price : prices) {
                        priceText = price.text();
                        for (Element ratingsElement : ratingsElements) {
                            rateText = ratingsElement.text();
                        }
                    }
                }
                for (Element merchantStore : merchantStores) {
                    storeText = merchantStore.text();
                }
                productList.add(new String[] { titleText, priceText, rateText, storeText });
            }
            CSVWriter writer = new CSVWriter(new FileWriter("List of Top 100 Smartphone Product From Tokopedia.csv"));
            String[] header = { "Product", "Price", "Rating", "Merchant Store" };
            writer.writeNext(header);
            writer.writeAll(productList, true);
            writer.close();
        }
    }
}
