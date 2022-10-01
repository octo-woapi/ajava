package com.octo.ajava.fixture;

public class TMDBMoviesFixture {

  public static String deuxFilmsDeTMDBEnJSON() {
    return """
                {
                  "page": 1,
                  "total_results": 19629,
                  "total_pages": 982,
                  "results": [
                    {
                      "poster_path": "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                      "adult": false,
                      "overview": "From DC Comics comes the Suicide Squad...",
                      "release_date": "2016-08-03",
                      "genre_ids": [
                        14,
                        28,
                        80
                      ],
                      "id": 297761,
                      "original_title": "Suicide Squad",
                      "original_language": "en",
                      "title": "Suicide Squad",
                      "backdrop_path": "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
                      "popularity": 48.261451,
                      "vote_count": 1466,
                      "video": false,
                      "vote_average": 5.91
                    },
                    {
                      "poster_path": "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                      "adult": false,
                      "overview": "The most dangerous former operative of the CIA...",
                      "release_date": "2016-07-27",
                      "genre_ids": [
                        28,
                        53
                      ],
                      "id": 324668,
                      "original_title": "Jason Bourne",
                      "original_language": "en",
                      "title": "Jason Bourne",
                      "backdrop_path": "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
                      "popularity": 30.690177,
                      "vote_count": 649,
                      "video": false,
                      "vote_average": 5.25
                    }
                  ]
                }
                """;
  }
}
