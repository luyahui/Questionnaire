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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testFindById(){
        Question q = questionService.find(1);

        Assert.assertNotNull(q);

        System.out.println(q);
    }

    @Test
    public void testSave() {
        Question q = new Question();
        q.setType(QuestionType.TRIVIA);
        q.setDescription("Which team won the 2017 super bowl?");
        q.setOptions("Falcons" + "\n" + "Patriots");

        Assert.assertNotNull(questionService.save(q));
    }

    @Test
    public void testUpdate(){
        Question q = questionService.find(1);
        q.setOptions("Patriots");

        Assert.assertEquals(questionService.save(q).getOptions(), "Patriots");
    }

    @Test
    public void testRandom(){
        Question q = questionService.getRandom("");
        Assert.assertNotNull(q);
        System.out.println(q);
    }
}
