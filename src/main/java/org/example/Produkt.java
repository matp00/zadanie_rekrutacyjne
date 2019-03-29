package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Produkt {

    private String name;
    private Double cena;
    private Double przelicznikRabatu;
    private Double wartoscRabatu;

    public Double getWartoscRabatu() {
        return wartoscRabatu;
    }

    public void setWartoscRabatu(Double wartoscRabatu) {
        this.wartoscRabatu = ustawSkale(wartoscRabatu);
    }

    public Produkt(String name, Double cena) {
        this.name = name;
        this.cena = cena;
    }

    public String getName() {
        return name;
    }

    public Double getCena() {
        return cena;
    }


    public Double getPrzelicznikRabatu() {
        return przelicznikRabatu;
    }

    public void setPrzelicznikRabatu(Double przelicznikRabatu) {
        this.przelicznikRabatu = przelicznikRabatu;
    }

    public static Double kosztProduktow(List<Produkt> produkty){
        return produkty.stream()
                .map(Produkt::getCena)
                .reduce((s1,s2)->s1+s2).orElse(0d);
    }

    public static List<Produkt> obliczWartoscRabatu(List<Produkt> productsData, double rabat){
        Double kosztWszystkichProduktow = Produkt.kosztProduktow(productsData);
        return productsData.stream()
                .map(elem ->
                        {
                            elem.setPrzelicznikRabatu(elem.getCena()/kosztWszystkichProduktow);
                            elem.setWartoscRabatu(elem.getPrzelicznikRabatu() * rabat);
                            return elem;
                        }
                )
                .collect(Collectors.toList());
    }

    public static Double ustawSkale(Double d){
        return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static boolean isValidate(List<Produkt> produkty){
        return produkty.size() > 5 || produkty.stream().anyMatch(elem-> elem.getCena() <= 0 || elem.getName().isEmpty());
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "name='" + name + '\'' +
                ", cena=" + cena +
                ", przelicznikRabatu=" + przelicznikRabatu +
                ", wartoscRabatu=" + wartoscRabatu +
                '}';
    }
}
