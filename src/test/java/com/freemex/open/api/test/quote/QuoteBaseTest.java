package com.freemex.open.api.test.quote;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.test.BaseTests;

public class QuoteBaseTest extends BaseTests {
    public FreemexApiConfig config() {
        FreemexApiConfig config = new FreemexApiConfig();
        config.setEndpoint("https://api.bytemars.co");

        //config.setApiKey("affce6bfa93ef5fce2d8fd449e2fc26008d713a5ba2a26c25a40d7a24dc7e313");
        //config.setSecretKey("TF1ZW9PAOh8FM4x8FwI9okTaa8w0m3lxkJr5KGiGTbWQG3pPErvvRG5Zy8OcZtRQ");

        return config;
    }
}
