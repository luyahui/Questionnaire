import com.lu.Application;
import com.lu.service.QuestionService;
import com.lu.model.Question;
import com.lu.model.QuestionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testFindById() {
        Question q = questionService.find(1);

        Assert.assertNotNull(q);

        System.out.println(q);
    }

    @Test
    public void testSave() {
        Question q = new Question();
//        q.setType(QuestionType.TRIVIA);
//        q.setDescription("Which team won the 2017 super bowl?");
//        q.setOptions("Falcons" + "\n" + "Patriots");
//        q.setCorrectOption("Patriots");

//        q.setType(QuestionType.POLL);
//        q.setDescription("What's your favorite car brand?");
//        q.setOptions("Nissan\n" + "Honda\n" + "Audi\n" + "BMW");

//        q.setType(QuestionType.CHECKBOX);
//        q.setDescription("What are the colors do you like?");
//        q.setOptions("Red\n" + "Blue\n" + "Yellow\n" + "Green\n" + "Black\n" + "Purple");

        q.setType(QuestionType.MATRIX);
        q.setDescription("Please tell us a bit about yourself");
        q.setOptions("Age\t<18\t18 to 35\t35 to 55\t>55\n" +
                "Gender\tMale\tFemale\n" +
                "Income(per year)\t<55000\t55000 to 85000\t85000 to 105000\t>105000");
        Assert.assertNotNull(questionService.save(q));
    }

    @Test
    public void testUpdate() {
        Question q = questionService.find(1);
        q.setOptions("Patriots");

        Assert.assertEquals(questionService.save(q).getOptions(), "Patriots");
    }

    @Test
    public void testRandom() {
//        String uuid = UUID.randomUUID().toString();
        String uuid = "fb09c589-6214-4362-a063-e4af295d0b28";

        Question q = questionService.getRandom(uuid);

        Assert.assertNotNull(q);
//        System.out.println(q);
//        Assert.assertNull(q);
    }
}
