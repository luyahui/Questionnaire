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
    public void testSave(){
        Record record = new Record();
        String uuid = UUID.randomUUID().toString();

        record.setUuid(uuid);
        record.setQuestion(questionService.find(1));
        record.setAnswer("Falcons");

        Assert.assertNotNull(recordService.save(record));

    }
}
