import com.lu.Application;
import com.lu.service.QuestionService;
import com.lu.service.RecordService;
import com.lu.model.Record;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RecordServiceTest {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private RecordService recordService;

    @Test
    public void testSave() {
        Record record = new Record();
        String uuid = UUID.randomUUID().toString();

        record.setUuid(uuid);
        record.setQuestion(questionService.find(1));
        record.setAnswer("Falcons");

        Assert.assertNotNull(recordService.save(record));

    }

    @Test
    public void testExists() {
//        String uuid = UUID.randomUUID().toString();
        String uuid = "fb09c589-6214-4362-a063-e4af295d0b28";
        long qid = 1;

        Assert.assertTrue(recordService.exists(uuid, qid));
    }

    @Test
    public void testFindByUuid(){

        String uuid = "fb09c589-6214-4362-a063-e4af295d0b28";
        Assert.assertEquals(recordService.findByUuid(uuid, 0, 10).getContent().size(), 1);
    }
}
