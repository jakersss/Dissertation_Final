package com.example.jakers.dissertation_work;

import org.junit.Test;

import java.util.ArrayList;
import static java.util.Arrays.asList;

import static org.junit.Assert.*;

/**
 * Created by Jakers on 18/11/2017.
 */

public class calendarTest {

    // Test ID = 1
    // Problems (fixed) - 18/11/2017: ArrayOutOfBounds (11) - fixed by changing elements in the arrayList. had missed "May",so size was smaller than expected.
    @Test
    public void monthNumberShouldEqualMonthString(){
        calendar calTest = new calendar();
        ArrayList month = calTest.months;
        int monthNum = 1;
        assertEquals(month.get(0), "January");
        assertEquals(month.get(11), "December");
        assertEquals(month.get(monthNum-1), "January");
}}