import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestInstructor {

    private IAdmin admin;
    private IStudent student;
    private IInstructor instructor;

    @Before
    public void setup() {

        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();

        this.admin.createClass("145",2017,"Matloff",40);




    }


    /**
     * test the same instructor as the class specifiied
     */
    @Test
    public void testAddHomework1() {
        this.instructor.addHomework("Matloff","145",2017,"HW1","Python");

        assertTrue(this.instructor.homeworkExists("145",2017,"HW1"));

    }

    /**
     * test the different instructor as the class specifiied
     */
    @Test
    public void testAddHomework2(){

        this.instructor.addHomework("Nitta","145",2017,"HW1","C");

        assertFalse(this.instructor.homeworkExists("145",2017,"HW1"));


    }


    /**
     * assign grade to student who submitted Homework
     */
    @Test
    public void testAssignGrade1(){


        this.student.registerForClass("Ken","145",2017);

        this.instructor.addHomework("Matloff","145",2017,"HW2","R");

        this.student.submitHomework("Ken","HW2","A1","145",2017);

        this.instructor.assignGrade("Matloff","145",2017,"HW2","Ken",100);


        assertTrue((this.instructor.getGrade("145",2017,"HW2","Ken") != null));


    }

    /**
     * assign grade to student who didn't submitted Homework
     */
    @Test
    public void testAssignGrade2(){

        this.student.registerForClass("John","145",2017);

        this.instructor.addHomework("Matloff","145",2017,"HW3","R");

        this.instructor.assignGrade("Matloff","145",2017,"HW3","John",40);


        assertFalse((this.instructor.getGrade("145",2017,"HW3","John") != null));

    }


    /**
     * assign grade to not assigned homework
     */

    @Test
    public void testAssignGrade3(){

        this.student.registerForClass("Kim","145",2017);

        this.instructor.assignGrade("Matloff","145",2017,"HW4","Kim",50);

        assertFalse((this.instructor.getGrade("145",2017,"HW4","Kim") != null));
    }


    /**
     * assign grade from other instructor
     */

    @Test
    public void testAssignGrade4(){



        this.student.registerForClass("Yun","145",2017);

        this.instructor.addHomework("Matloff","145",2017,"HW5","R");

        this.student.submitHomework("Yun","HW5","A1","145",2017);


        this.instructor.assignGrade("Nitta","145",2017,"HW5","Yun",70);

        assertFalse((this.instructor.getGrade("145",2017,"HW5","Yun") != null ));
    }




}