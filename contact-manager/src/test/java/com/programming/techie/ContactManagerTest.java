package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;
    private int testNumber = 0;

    private static List<String> phoneNumberList(){
        return Arrays.asList("1123123123","02312533212","0123b21122");
    }
    @BeforeAll
    public void shouldPrintBeforeAll(){
        System.out.println("Should print before all tests");
    }
    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    //
    //Welcome Messages
    //
    @Nested
    class welcomeMessagesNestedTest{
        @Test
        @DisplayName("Should print on linux")
        @EnabledOnOs(value = OS.LINUX,disabledReason = "Enabled Only On LINUX")
        public void shouldPrintOnLinux(){
            System.out.println("Welcome linux user!");
        }

        @Test
        @DisplayName("Should print on windows")
        @EnabledOnOs(value = OS.WINDOWS,disabledReason = "Enabled Only On WINDOWS")
        public void shouldPrintOnWindows(){
            System.out.println("Welcome windows user!");
        }

        @Test
        @DisplayName("Should print on mac")
        @EnabledOnOs(value = OS.MAC,disabledReason = "Enabled Only On MAC")
        public void shouldPrintOnMac(){
            System.out.println("Welcome MAC user!");
        }
    }


    //
    //Actual tests
    //
    @Test
    @DisplayName("Should Create Contact")
    public void shouldCreateContact(){
        contactManager.addContact("Jane","Doe", "0123123123");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        assertTrue(contactManager.getAllContacts().stream().anyMatch(
                contact -> contact.getFirstName().equals("Jane") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123123123"))
        );
    }
    @RepeatedTest(value = 5,name ="Repeating test {currentRepetition} of {totalRepetitions}")
    @DisplayName("Should Create Contact 5 times")
    public void shouldCreateContactFiveTimes(){
        contactManager.addContact("Jane","Doe", "0123123123");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
    @Nested
    class phoneNumberNestedTest{
        @BeforeEach
        public void phoneNumberTestMessage(){
            System.out.print("\nPhone Test");
        }
        @DisplayName("Should Create Contact using ParameterizedTest")
        @ParameterizedTest
        @ValueSource(strings = {"0123123123","0231253321","0123921122"})
        public void shouldCreateContactWithParametrizedPhoneNumber(String phoneNumber){
            contactManager.addContact("Jane","Doe", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
        @DisplayName("Should Throw RuntimeException using MethodSource")
        @ParameterizedTest
        @MethodSource("com.programming.techie.ContactManagerTest#phoneNumberList")
        public void shouldThrowRuntimeExceptionWithParametrizedPhoneNumber(String phoneNumber){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("Jane","Doe", phoneNumber)
            );
        }
        @DisplayName("Should Throw RuntimeException using CSVSource")
        @ParameterizedTest
        @CsvSource({"h","abd","012921129"}) //CommaSeperatedStringValues
        public void shouldThrowRuntimeExceptionWithCSVSource(String phoneNumber){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("Jane","Doe", phoneNumber)
            );
        }
        @DisplayName("Should Throw RuntimeException using CSVFileSource")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldThrowRuntimeExceptionWithCSVFileSource(String phoneNumber){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("Jane","Doe", phoneNumber)
            );
        }
    }

    @Test
    @DisplayName("Should be aborted")
    public void shouldBeAborted(){
        Assumptions.assumeTrue(false);
        contactManager.addContact("John","Doe", "0123123123");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Nested
    class checkIfContactParamsAreNullOrEmpty{
        @Test
        @DisplayName("Should Throw RuntimeException When Last Name Is Null")
        public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("Jane",null, "0123123123")
            );
        }

        @Test
        public void shouldThrowRuntimeExceptionWhenLastNameIsEmpty(){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("Jane","", "0123123123")
            );
        }

        @Test
        public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact(null,"Doe", "0123123123")
            );
        }

        @Test
        public void shouldThrowRuntimeExceptionWhenFirstNameIsEmpty(){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("","Doe", "0123123123")
            );
        }

        @Test
        public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
            assertThrows(RuntimeException.class,
                    ()->contactManager.addContact("John","Doe", null)
            );
        }

    }

    @AfterEach
    public void shouldPrintAfterEachTest(){
        testNumber++;
        System.out.print("\nTest ");
        System.out.print(testNumber);
        System.out.print(" completed");
    }
    @AfterAll
    public void shouldPrintAfterAllTest(){
        System.out.println("\n\nTests completed");
    }
}