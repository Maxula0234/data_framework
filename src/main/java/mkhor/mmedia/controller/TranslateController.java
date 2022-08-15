package mkhor.mmedia.controller;

import io.swagger.v3.oas.annotations.Hidden;
import mkhor.mmedia.dto.request.TranslateReq;
import mkhor.mmedia.dto.response.translate.TranslateResult;
import mkhor.mmedia.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("translate")
@Hidden
public class TranslateController {

    @Autowired
    TranslateService translateService;

    @PostMapping
    public TranslateResult postTranslate(@RequestBody TranslateReq translateReq) {
        TranslateResult translateResult = translateService.postTranslate(translateReq);
        return translateResult;
    }
}
