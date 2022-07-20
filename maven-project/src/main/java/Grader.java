public class Grader {
    public char getLetterGradeFromNumber(int numberGrade){
        if(numberGrade < 0 || numberGrade > 100){
            throw new IllegalArgumentException("Number must be in 0-100 range");
        }

        if(numberGrade < 60){
            return 'F';
        }else if(numberGrade < 70){
            return 'D';
        }else if(numberGrade < 80){
            return 'C';
        }else if(numberGrade < 90){
            return 'B';
        }else{
            return 'A';
        }
    }
}
