package hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @GetMapping("/date/compare/{date}")
	public String compareDate(@PathVariable("date")String strDate) {
    	
    	String content = "";
    	SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    	try {
    		Date today = new Date();
			Date d1 = sd.parse(strDate);
			
		    long diff = today.getTime() - d1.getTime();
		    long diffDate = (diff / (1000 * 60 * 60 * 24));
			
			content = Long.toString(diffDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return content;
    }
}
