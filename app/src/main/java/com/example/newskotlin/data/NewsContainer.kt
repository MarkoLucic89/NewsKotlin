package com.example.newskotlin.data

import com.example.newskotlin.model.Category
import com.example.newskotlin.model.News

class NewsContainer {

    companion object {


        var newsList: ArrayList<News> = ArrayList()
        var categoryList: ArrayList<Category> = ArrayList()

        fun loadNews() {
            newsList.add(News("SKUPA FOTKA! JOKIĆ JE S NJIMA SLAVIO ISTORIJSKI USPEH",
                "Posle neverovatne partije protiv Pelikansa Nikola Jokić je uspeh proslavio na skroman način.",
                """
         Posle utakmice je usledila skromna proslava u krugu najbližih, a njegov menadžer Miško Ražnatović je objavio fotografiju koja je ubrzo postala hit. Na njoj se pored Nikole i agenta nalaze i Jokićeva braća Nemanja i Strahinja, Nemanja Bjelica, Dejan Milojević, Vlatko Čančar i Žarko Drašković.
         
         "Skromna proslava sa dobro poznatim društvom u Denveru. Istorijski tripl-dabl Jokića. U istoriji lige nijedan igrač nije ponovio Nikolin učinak. Samo u četvrtoj četvrtini i produžetku je dao 30 poena sa šutem 10 od 11", napisao je Ražnatović na Instagramu.
         """.trimIndent(),
                "https://static.mondo.rs/Picture/1119813/jpeg/Jokic-Raznatovic-Bjelica-i-Milojevic.jpg?ts=2022-03-07T11:46:51",
                "Sport",
                "mondo.rs",
                "https://mondo.rs/Sport/Kosarka/a1606060/Nikola-Jokic-na-veceri-sa-bracom-Raznatovicem-Bjelicom-i-Milojevicem.html",
                "07.03.2022. / 11:53"))
            newsList.add(News("AMERIKA TRAŽI SPAS U VENECUELI! Hoće da zamene Putinovu naftu",
                "Predsednik Venecuele Nikolas Maduro tvrdi da zemlja trenutno proizvodi oko milion barela sirove nafte dnevno.",
                "",
                "https://static.mondo.rs/Picture/1120569/jpeg/Nicolas-Maduro.jpg?ts=2022-03-09T09:49:55",
                "Politika",
                "mondo.rs",
                "https://mondo.rs/Info/Svet/a1606973/Sastanak-Venecuele-i-Amerike-zbog-nafte.html",
                "09.03.2022. / 10:01"))
            newsList.add(News("PROMENIO SAM KANAL KADA JE KONSTRAKTA ZAPEVALA!",
                "Saša Popović izneo je svoje mišljenje o pobednici na izboru za Pesmu Evrovizije, Ani Đurić, svima već dobro poznatoj kao Konstrakta.",
                "",
                "https://static.mondo.rs/Picture/1120566/png/sasa-popovic.jpg?ts=2022-03-09T09:32:26",
                "Zabava",
                "mondo.rs",
                "https://mondo.rs/Zabava/Muzika/a1606991/Sasa-Popovic-o-Konstrakti.html",
                "09.03.2022. / 10:01"))
            newsList.add(News("NOVAK IGRA U AMERICI? OGLASIO SE TURNIR, SVE SU OBJASNILI",
                "Još uvek čekamo poslednje informacije o vizi!",
                "",
                "https://static.mondo.rs/Picture/1109093/jpeg/novak-djokovic.jpeg?ts=2022-02-09T23:45:14",
                "Sport",
                "mondo.rs",
                "https://mondo.rs/Sport/Tenis/a1606922/Novak-Djokovic-zreb-za-Indijan-Vels.html",
                "09.03.2022. / 07:00"))
            newsList.add(News("14. DAN INVAZIJE NA UKRAJINU Novi EU udar na ruske oligarhe",
                "Četrnaestog dana ruske invazije nastavilo se sa napadima na ukrajinske gradove. Za danas je najavljen privremeni prekid vatre kako bi se obezbedila bezbedna evakuacija ukrajinskih civila. ",
                "",
                "https://ocdn.eu/pulscms-transforms/1/T4Wk9kpTURBXy9mZmI0NGRmOGEwZDFmNTNkMTM2YjNmOTIzMmIxYTk3My5qcGeRkwLNAxYAgaEwBQ",
                "Politika",
                "blic.rs",
                "https://www.blic.rs/vesti/svet/uzivo-14-dan-invazije-na-ukrajinu-novi-eu-udar-na-ruske-oligarhe-banka-u-rusiji/grlstnh",
                "09.03.2022. / 07:00"))
            newsList.add(News("Nepravda prema Jokiću i TEŠKO NEZNANJE američkog novinara",
                "Maks Kelerman, poznati novinar ESPN-a, izjavio je da Nikola Jokić mora da unapredi igru u plej-ofu u odnosu na ligaški deo, što je samo potvrda da \"mejnstrim\" novinari u Americi ne prate dovoljno Jokića i Nagets, već samo ponavljaju utabani narativ oblikovan raznim predrasudama.",
                "",
                "https://ocdn.eu/pulscms-transforms/1/HcIk9kqTURBXy81ODJhMGYyOGUyOTM5NTc2YzVlMjQyOGZkNDgzMDhiMS5qcGVnkZMCzQMWAIGhMAU",
                "Sport",
                "blic.rs",
                "https://sport.blic.rs/kosarka/nba/nepravda-prema-jokicu-i-tesko-neznanje-americkog-novinara-o-kome-bruji-ceo-denver/50rhkm7",
                "09.03.2022. / 07:00"))
            newsList.add(News("HAOS PRED ŽREB ZA MUNDIJAL: FIFA donela novu odluku",
                "Žreb za Svetsko prvenstvo u Kataru biće održan 1. aprila, aliveć sada je jasno da će u \"bubnju\" biti samo 30 od 32 učesnika turnira, pa nije isključeno da Srbija čak do juna ne zna sa kim sve igra na Mundijalu!",
                "",
                "https://ocdn.eu/pulscms-transforms/1/X8bk9kpTURBXy9jZDRmZDM4NmYzMmFiN2M1YjljZTM4ZWQzZWMyNWYxZi5qcGeRkwLNAxYAgaEwBQ",
                "Sport",
                "blic.rs",
                "https://sport.blic.rs/fudbal/svetski-fudbal/haos-pred-zreb-za-mundijal-fifa-donela-novu-odluku-srbija-mozda-do-juna-nece-znati-s/79kzwlx",
                "09.03.2022. / 07:00"))


        }

        fun loadCategoryList() {
            categoryList.add(Category("Sport", "#F44336"))
            categoryList.add(Category("Politika", "#FFEB3B"))
            categoryList.add(Category("Zabava", "#BB86FC"))
        }

        fun filterList(category: String): ArrayList<News> {
            var list: ArrayList<News> = ArrayList()

            for (news in newsList) {
                if (news.category.name == category) {
                    list.add(news)
                }
            }

            return list
        }


    }
}