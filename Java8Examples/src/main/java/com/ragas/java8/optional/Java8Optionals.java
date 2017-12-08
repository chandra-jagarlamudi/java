package com.ragas.java8.optional;

import java.util.Optional;

/**
 * Where does Java8 Optional fits?
 *
 * 1. Method Parameter
 *  public void setResolution(Optional<ScreenResolution> resolution) {this.resolution = resolution;}
 *
 * 2. Method Return Type
 *  public Optional<ScreenResolution> getResolution() {return resolution;}
 *
 * 3. Constructor Parameter
 *  public DisplayFeatures(String size, Optional<ScreenResolution> resolution){this.size = size; this.resolution = resolution;}
 *
 * 4. Variable Declaration
 *  private Optional<ScreenResolution> resolution;
 *
 * 5. Class Level
 *  public class A<T extends Optional<B>> { }
 *
 */

public class Java8Optionals {
    public static void main(String[] args) {

        Optional<String> gender = Optional.of("Chandra");
        String answer1 = "Yes";
        String answer2 = null;

        System.out.println("Non-Empty Optional                  ::" + gender);
        System.out.println("Non-Empty Optional: Gender value    :: " + gender.get());
        System.out.println("Empty Optional                      :: " + Optional.empty());

        System.out.println("ofNullable on Non-Empty Optional    :: " + Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty Optional        :: " + Optional.ofNullable(answer2));

        // java.lang.NullPointerException
        //System.out.println("ofNullable on Non-Empty Optional    :: " + Optional.of(answer2));

        Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println("Non-Empty Optional                  :: " + nonEmptyGender.map(String::toUpperCase));
        System.out.println("Empty Optional                      :: " + emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOptionalGender = Optional.of(Optional.of("male"));
        System.out.println("Optional value                      :: " + nonEmptyOptionalGender);
        System.out.println("Optional.map                        :: " + nonEmptyOptionalGender.map(gen -> gen.map(String::toUpperCase)));
        System.out.println("Optional.flatMap                    :: " + nonEmptyOptionalGender.flatMap(gen -> gen.map(String::toUpperCase)));

    }
}
