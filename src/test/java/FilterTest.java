import org.infai.seits.sepl.operators.Message;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class FilterTest {

    @Test
    public void stringEqualTrue() throws IOException {
        Filter filter = new Filter("\"foobar\"");
        Message msg = TestMessageProvider.getTestMessage("foobar");
        filter.config(msg);
        filter.run(msg);
        Assert.assertTrue((new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered"));
    }

    @Test
    public void stringEqualFalse() throws IOException {
        Filter filter = new Filter("\"foobar\"");
        Message msg = TestMessageProvider.getTestMessage("foo");
        filter.config(msg);
        filter.run(msg);
        try{
            (new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered");
            Assert.fail("expected error");
        }catch (Exception e) { }
    }

    @Test
    public void numberEqualTrue() throws IOException {
        Filter filter = new Filter("42");
        Message msg = TestMessageProvider.getTestMessage(42);
        filter.config(msg);
        filter.run(msg);
        Assert.assertTrue((new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered"));
    }

    @Test
    public void floatEqualTrue() throws IOException {
        Filter filter = new Filter("4.2");
        Message msg = TestMessageProvider.getTestMessage(4.2);
        filter.config(msg);
        filter.run(msg);
        Assert.assertTrue((new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered"));
    }

    @Test
    public void floatEqualTrue2() throws IOException {
        Filter filter = new Filter("42.0");
        Message msg = TestMessageProvider.getTestMessage(42);
        filter.config(msg);
        filter.run(msg);
        Assert.assertTrue((new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered"));
    }

    @Test
    public void floatEqualTrue3() throws IOException {
        Filter filter = new Filter("42");
        Message msg = TestMessageProvider.getTestMessage(42.0);
        filter.config(msg);
        filter.run(msg);
        Assert.assertTrue((new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered"));
    }


    @Test
    public void floatEqualFalse() throws IOException {
        Filter filter = new Filter("4.2");
        Message msg = TestMessageProvider.getTestMessage(13);
        filter.config(msg);
        filter.run(msg);
        try{
            (new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered");
            Assert.fail("expected error");
        }catch (Exception e) { }
    }

    @Test
    public void numberEqualFalse() throws IOException {
        Filter filter = new Filter("42");
        Message msg = TestMessageProvider.getTestMessage(13);
        filter.config(msg);
        filter.run(msg);
        try{
            (new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered");
            Assert.fail("expected error");
        }catch (Exception e) { }
    }

    @Test
    public void stringNumber() throws IOException {
        Filter filter = new Filter("\"foobar\"");
        Message msg = TestMessageProvider.getTestMessage(42);
        filter.config(msg);
        filter.run(msg);
        try{
            (new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered");
            Assert.fail("expected error");
        }catch (Exception e) { }
    }

    @Test
    public void numberString() throws IOException {
        Filter filter = new Filter("42");
        Message msg = TestMessageProvider.getTestMessage("foo");
        filter.config(msg);
        filter.run(msg);
        try{
            (new JSONObject(msg.getMessageString())).getJSONObject("analytics").getBoolean("filtered");
            Assert.fail("expected error");
        }catch (Exception e) { }
    }
}