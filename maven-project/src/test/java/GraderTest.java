import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraderTest {
    //errors
    @Test
    void negativeShouldReturnIllegalArgumentException(){
        Grader grader = new Grader();
        assertThrows(IllegalArgumentException.class,
                ()-> grader.getLetterGradeFromNumber(-1)
        );
    }
    @Test
    void overHundredShouldReturnIllegalArgumentException(){
        Grader grader = new Grader();
        assertThrows(IllegalArgumentException.class,
                ()-> grader.getLetterGradeFromNumber(101)
        );
    }

    //boundaries
    @Test
    void zeroShouldReturnF(){
        Grader grader = new Grader();
        assertEquals('F',grader.getLetterGradeFromNumber(0));
    }
    @Test
    void fiftyNineShouldReturnF(){
        Grader grader = new Grader();
        assertEquals('F',grader.getLetterGradeFromNumber(59));
    }
    @Test
    void sixtyShouldReturnD(){
        Grader grader = new Grader();
        assertEquals('D',grader.getLetterGradeFromNumber(60));
    }
    @Test
    void sixtyNineShouldReturnD(){
        Grader grader = new Grader();
        assertEquals('D',grader.getLetterGradeFromNumber(69));
    }
    @Test
    void seventyShouldReturnC(){
        Grader grader = new Grader();
        assertEquals('C',grader.getLetterGradeFromNumber(70));
    }
    @Test
    void seventyNineShouldReturnC(){
        Grader grader = new Grader();
        assertEquals('C',grader.getLetterGradeFromNumber(79));
    }
    @Test
    void eightyShouldReturnB(){
        Grader grader = new Grader();
        assertEquals('B',grader.getLetterGradeFromNumber(80));
    }
    @Test
    void eightyNineShouldReturnB(){
        Grader grader = new Grader();
        assertEquals('B',grader.getLetterGradeFromNumber(89));
    }
    @Test
    void ninetyShouldReturnA(){
        Grader grader = new Grader();
        assertEquals('A',grader.getLetterGradeFromNumber(90));
    }
    @Test
    void hundredShouldReturnA(){
        Grader grader = new Grader();
        assertEquals('A',grader.getLetterGradeFromNumber(100));
    }

}