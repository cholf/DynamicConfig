import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-19
 * Time: 下午4:24
 * 类描述:
 */
public class AsyncContextTest {
    public static void main(String[] args) {
        HttpServletRequest request=null;
        AsyncContext ctx = request.getAsyncContext();
    }

}
