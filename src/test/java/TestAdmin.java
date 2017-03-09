import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestAdmin {

    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {

        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClass1() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }


    /**
     *test unique class
     */
    @Test
    public void testMakeClass3(){
        this.admin.createClass("150", 2017, "Nitta", 30);
        this.admin.createClass("150", 2017, "John", 30);
        assertFalse(this.admin.getClassInstructor("150",2017).equals("John"));
    }


    /**
     *test unique class 2
     */

    @Test
    public void testMakeClass4(){
        this.admin.createClass("120", 2017, "Gysel", 30);
        this.admin.createClass("120", 2017, "Gysel", 40);
        assertFalse((this.admin.getClassCapacity("120",2017) == 40 ));
    }



    /**
     *test no more than two can be assigned to one professor in the same year
     */

    @Test
    public void testMakeClass5(){
        this.admin.createClass("145", 2017, "Matloff", 40);
        this.admin.createClass("132", 2017, "Matloff", 40);
        this.admin.createClass("160", 2017, "Matloff", 40);


        // assertFalse(this.admin.getClassInstructor("145",2017)
        //  .equals(this.admin.getClassInstructor("160",2017)));
        assertFalse(this.admin.classExists("160",2017));
    }

    /**
     *test capacity is > 0
     */
    @Test
    public void testMakeClass6(){
        this.admin.createClass("189", 2017, "Devanbu", -1);
        assertFalse(this.admin.classExists("189",2017));
    }


    /**
     * test changeCapacity set to the less number of students enrolled
     */
    @Test
    public void testChangeCapacity1(){
        this.admin.createClass("193", 2017, "Lu", 40);


        for (int i = 0 ; i < 10 ; i++){
            this.student.registerForClass("Student" + Integer.toString(i),"193",2017);
        }


        this.admin.changeCapacity("193",2017,9);

        assertFalse((this.admin.getClassCapacity("193",2017) == 9));
    }

    /**
     * test changeCapacity set to the same number of students enrolled
     */
    @Test
    public void testChangeCapacity2(){
        this.admin.createClass("122A", 2017, "Bai", 40);

        for (int i = 0 ; i < 10 ; i++){
            this.student.registerForClass("Student" + Integer.toString(i),"122A",2017);
        }


        this.admin.changeCapacity("122A",2017,10);

        assertTrue((this.admin.getClassCapacity("122A",2017) == 10));

    }


    /**
     * test changeCapacity set to the greater than number of students enrolled
     */
    @Test
    public void testChangeCapacity3(){
        this.admin.createClass("122B", 2017, "Bai", 40);
        for (int i = 0 ; i < 10 ; i++){
            this.student.registerForClass("Student" + Integer.toString(i),"122B",2017);
        }


        this.admin.changeCapacity("122B",2017,20);

        assertTrue((this.admin.getClassCapacity("122B",2017) == 20));
    }








}

