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

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestStudent {

    private IAdmin admin;
    private IStudent student;
    private IInstructor instructor;

    @Before
    public void setup() {

        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();


        this.admin.createClass("145",2017,"Matloff",5);
        this.instructor.addHomework("Matloff","145",2017,"HW1","Python");


    }

    /**
     *  this class exists and has not met its enrolment capacity.
     */

    @Test
    public void testRegisterForClass1(){

        for (int i = 0 ; i < 4 ; i++){
            this.student.registerForClass("Student" + Integer.toString(i),"145",2017);
        }


        this.student.registerForClass("Ken","145",2017);

        assertTrue(this.student.isRegisteredFor("Ken","145",2017));

    }

    /**
     *  this class doens't exists
     */

    @Test
    public void testRegisterForClass2(){

        this.student.registerForClass("Ken","160",2017);

        assertFalse(this.student.isRegisteredFor("Ken","160",2017));
    }

    /**
     * this class  has not met its enrolment capacity.
     */

    @Test
    public void testRegisterForClass3(){
        for (int i = 0 ; i < 5 ; i++){
            this.student.registerForClass("Student" + Integer.toString(i),"145",2017);
        }


        this.student.registerForClass("John","145",2017);

        assertFalse(this.student.isRegisteredFor("John","145",2017));
    }

    /**
     * drop students is registered
     */
    @Test
    public void testDropClass1(){
        this.student.registerForClass("James","145",2017);

        this.student.dropClass("James","145",2017);

        assertFalse(this.student.isRegisteredFor("James","145",207));

    }

    /**
     * drop students is not registered
     */
    @Test
    public void testDropClass2(){


        this.student.dropClass("Yun","145",2017);

        assertFalse(this.student.isRegisteredFor("Yun","145",207));

    }

    /**
     * homework exists, student is registered and the class is taught in the current year
     */
    @Test
    public void testSubmitHomework1(){
        this.student.registerForClass("Tom","145",2017);
        this.student.submitHomework("Tom","HW1","A1","145",2017);

        assertTrue(this.student.hasSubmitted("Tom","HW1","145",2017));
    }

    /**
     * homework doest exists, student is registered and the class is taught in the current year
     */
    @Test
    public void testSubmitHomework2(){
        this.student.registerForClass("Eason","145",2017);
        this.student.submitHomework("Eason","HW2","A1","145",2017);

        assertFalse(this.student.hasSubmitted("Eason","HW2","145",2017));
    }

    /**
     * homework exists, student is not registered submit to the class
     */
    @Test
    public void testSubmitHomework3(){

        this.admin.createClass("122",2017,"Bai",5);

        this.student.registerForClass("Sally","122",2017);

        this.student.submitHomework("Sally","HW1","A1","145",2017);

        assertFalse(this.student.hasSubmitted("Sally","HW1","145",2017));
    }





}

