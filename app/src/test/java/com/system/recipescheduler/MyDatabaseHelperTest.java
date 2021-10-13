package com.system.recipescheduler;

import android.content.Context;
import com.system.recipescheduler.database.MyDatabaseHelperExample;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.params.*;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MyDatabaseHelperTest {

    @Mock
    Context context;

    private MyDatabaseHelperExample helperTest;

    @BeforeEach
    void setUp() {
        helperTest = new MyDatabaseHelperExample(context);
    }

    @Disabled
    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Add two numbers")
    void add() {
        assertEquals(4, helperTest.add(2, 2));
    }


    @Test
    @DisplayName("Multiply two numbers")
    void multiply() {
        assertAll(() -> assertEquals(4, helperTest.multiply(2, 2)),
                () -> assertEquals(-4, helperTest.multiply(2, -2)),
                () -> assertEquals(4, helperTest.multiply(-2, -2)),
                () -> assertEquals(0, helperTest.multiply(1, 0)));
    }


    //TODO: Managed to get most of it working now with the tests and junit 5, now to try parameterized again
    // this youtube channel could help:
    // https://www.youtube.com/watch?v=ryBetrexwt4&t=130s

    @Test
    @DisplayName("Phone Number is valid")
    void validatePhoneNumber() {
        boolean isValid = helperTest.test("989804981");
        MatcherAssert.assertThat(isValid, is(true));
    }


    static List<String> EXPECTED_SYMBOLS = Arrays.asList(new String[]{"IBM", "APPL", "GOOGL"}); //just initialising array for test, not needed


    @ParameterizedTest(name = "{index}: order symbol ({0})") // Dynamic naming of the test
    @ValueSource(strings = {"IBM", "APPL", "GOOGL"})
    void testWithValueSource(String symbol) {
        MatcherAssert.assertThat(EXPECTED_SYMBOLS, hasItem(symbol));
    }


    @ParameterizedTest(name = "{index}: null and empty strings ({0})") // Dynamic naming of the test
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void nullEmptyAndBlankStrings(String text) {
        System.out.println("nullEmptyANdBlankStrings: " + text);
        assertTrue(text == null || text.trim().isEmpty());
    }


    @Disabled
    @Test
    void onCreate() {
    }

    @Disabled
    @Test
    void onUpgrade() {
    }

    @Disabled
    @Test
    void addBook() {
    }

    @Disabled
    @Test
    void readAllData() {
    }

    @Disabled
    @Test
    void updateData() {
    }

    @Disabled
    @Test
    void deleteOneRow() {
    }

    @Disabled
    @Test
    void deleteAllData() {
    }


}