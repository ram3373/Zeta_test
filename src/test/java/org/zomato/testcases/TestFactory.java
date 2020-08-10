package org.zomato.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class TestFactory {

    @DataProvider(name = "citiesparam")
    public Object[][] correct_Cities_Data() {
        return new Object[][]{{"Chennai"}, {"Bengaluru"}, {"Delhi"}};
    }

    @DataProvider(name = "citiesname_citiesID_and_CountryName")
    public Object[][] incorrect_CitiesID_Data() {
        return new Object[][]{{"Madras", "5", "India",5,1}, {"Delhi", "11", "India",5,3}};
    }
}
