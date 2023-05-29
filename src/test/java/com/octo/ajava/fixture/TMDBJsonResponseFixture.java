package com.octo.ajava.fixture;

public class TMDBJsonResponseFixture {

    public static String unFilm() {
        return """
                {
                    "adult": false,
                    "backdrop_path": "/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                    "belongs_to_collection": {
                        "id": 948485,
                        "name": "The Batman Collection",
                        "poster_path": "/tuzKA9K5Ae9IzaA0R9oAgbyhAI3.jpg",
                        "backdrop_path": "/qS2ViQwlWUECiAdkIuEaJZoq0QW.jpg"
                    },
                    "budget": 185000000,
                    "genres": [
                        {
                            "id": 80,
                            "name": "Crime"
                        },
                        {
                            "id": 9648,
                            "name": "Mystery"
                        },
                        {
                            "id": 53,
                            "name": "Thriller"
                        }
                    ],
                    "homepage": "https://www.thebatman.com",
                    "id": 414906,
                    "imdb_id": "tt1877830",
                    "original_language": "en",
                    "original_title": "The Batman",
                    "overview": "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
                    "popularity": 225.897,
                    "poster_path": "/74xTEgt7R36Fpooo50r9T25onhq.jpg",
                    "production_companies": [
                        {
                            "id": 101405,
                            "logo_path": null,
                            "name": "6th & Idaho",
                            "origin_country": "US"
                        },
                        {
                            "id": 119245,
                            "logo_path": "/6F45uVsKpNYANcWvplyhgIFezFU.png",
                            "name": "Dylan Clark Productions",
                            "origin_country": "US"
                        },
                        {
                            "id": 128064,
                            "logo_path": "/13F3Jf7EFAcREU0xzZqJnVnyGXu.png",
                            "name": "DC Films",
                            "origin_country": "US"
                        },
                        {
                            "id": 174,
                            "logo_path": "/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png",
                            "name": "Warner Bros. Pictures",
                            "origin_country": "US"
                        }
                    ],
                    "production_countries": [
                        {
                            "iso_3166_1": "US",
                            "name": "United States of America"
                        }
                    ],
                    "release_date": "2022-03-01",
                    "revenue": 770945583,
                    "runtime": 177,
                    "spoken_languages": [
                        {
                            "english_name": "English",
                            "iso_639_1": "en",
                            "name": "English"
                        }
                    ],
                    "status": "Released",
                    "tagline": "Unmask the truth.",
                    "title": "The Batman",
                    "video": false,
                    "vote_average": 7.71,
                    "vote_count": 7991
                }
                """;
    }

    public static String deuxFilms() {
        return """
                        {
                          "page": 1,
                          "results": [
                            {
                                "adult": false,
                                "backdrop_path": "/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                                "genre_ids": [
                                    80,
                                    9648,
                                    53
                                ],
                                "id": 414906,
                                "original_language": "en",
                                "original_title": "The Batman",
                                "overview": "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
                                "popularity": 153.181,
                                "poster_path": "/74xTEgt7R36Fpooo50r9T25onhq.jpg",
                                "release_date": "2022-03-01",
                                "title": "The Batman",
                                "video": false,
                                "vote_average": 7.71,
                                "vote_count": 7939
                            },
                            {
                               "adult": false,
                               "backdrop_path": "/ew5FcYiRhTYNJAkxoVPMNlCOdVn.jpg",
                               "genre_ids": [
                                   28,
                                   80,
                                   18
                               ],
                               "id": 272,
                               "original_language": "en",
                               "original_title": "Batman Begins",
                               "overview": "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption that plagues his home, Gotham City.  Unable to work within the system, he instead creates a new identity, a symbol of fear for the criminal underworld - The Batman.",
                               "popularity": 41.535,
                               "poster_path": "/4MpN4kIEqUjW8OPtOQJXlTdHiJV.jpg",
                               "release_date": "2005-06-10",
                               "title": "Batman Begins",
                               "video": false,
                               "vote_average": 7.695,
                               "vote_count": 19019
                           }
                          ],
                          "total_pages": 8,
                          "total_results": 146
                        }
                """;
    }
}
