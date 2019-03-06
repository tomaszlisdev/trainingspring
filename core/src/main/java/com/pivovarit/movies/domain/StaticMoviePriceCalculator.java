package com.pivovarit.movies.domain;

class StaticMoviePriceCalculator implements MoviePriceCalculator {

    @Override
    public long getPrice(MovieType movieType) {
        switch (movieType) {
            case NEW: return 42;
            case REGULAR: return 10;
            case OLD: return 1;
            default: throw new IllegalStateException("pricing not found");
        }
    }
}
