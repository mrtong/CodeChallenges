package com.foo.cba;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by yanjuntong on 31/01/16.
 * this test is requied to check whether movies are overloap
 */

public class MovieNight {
    public static Boolean canViewAll(Collection<Movie> movies) {
        boolean canViewall = true;

        ArrayList<Movie> moviesList = new ArrayList(movies);
        ArrayList<Movie> moviesList2 = new ArrayList(movies);
        moviesList2.add(moviesList.get(0));


        for(int i=0;i<moviesList.size();i++){
            Date endDate =  moviesList.get(i).getEnd();
            for(int j=i+1;j<moviesList.size();j++){

                if(endDate.after(moviesList.get(j).getStart())){
                    canViewall =false;
                }

            }

        }


        return canViewall;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2016-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));

        System.out.println(MovieNight.canViewAll(movies));
    }
}

class Movie {
    private Date start, end;

    public Movie(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }
}
