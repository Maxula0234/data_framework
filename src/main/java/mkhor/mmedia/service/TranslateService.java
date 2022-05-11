package mkhor.mmedia.service;

import mkhor.mmedia.dto.request.TranslateReq;
import mkhor.mmedia.dto.response.translate.TranslateResult;

public interface TranslateService {
    TranslateResult postTranslate(TranslateReq translateReq);
}
