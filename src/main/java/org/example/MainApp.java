package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MainApp {
    public static void main(String[] args) throws Exception {

        //Dane wejściowe
        double rabat = 100;

        List<Produkt> productsData = new ArrayList<>();
        productsData.add(new Produkt("blender", 0d));
        productsData.add(new Produkt("telewizor", 1500d));
        productsData.add(new Produkt("lodowka", 500d));
        productsData.add(new Produkt("telefon", 500d));
        productsData.add(new Produkt("pralka", 500d));

        //sprawdzam poprawnosc dostarczonych danych:
        //lista moze miec maxymalnie 5 produktow
        //kazdy element musi miec nazwe oraz cene wieksza niz 0
        if(Produkt.isValidate(productsData)){
            throw new Exception("\nDane wejsciowe sa nieprawidlowe:\n" +
                    "Lista produktow moze miec maxymalnie 5 elementow\n" +
                    "Nazwy produktow nie moga byc puste\n" +
                    "Ceny produktow muszą przekraczac 0");
        }

        List<Produkt> products = Produkt.obliczWartoscRabatu(productsData,rabat);

        double suma=products.stream()
                .map(Produkt::getWartoscRabatu)
                .reduce((s1,s2)->s1+s2).get();

        if(suma!=rabat){
            double roznica = Produkt.ustawSkale(rabat-suma);
            products.get(products.size()-1).setWartoscRabatu(products.get(products.size()-1).getWartoscRabatu()+roznica);
        }

        products.forEach(elem-> System.out.println(String.format("%s=%2.2fzl",elem.getName(),elem.getWartoscRabatu())));
    }
}



