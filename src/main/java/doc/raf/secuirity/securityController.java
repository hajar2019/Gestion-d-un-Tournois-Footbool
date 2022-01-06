package doc.raf.secuirity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
    @GetMapping(value = "/noAutoried")
    public String erreurPage(){
        return "erreurPage";
    }
}
