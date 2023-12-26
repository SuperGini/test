package com.gini.error.decoder;

import com.gini.error.exception.TrainsCoreClientException;
import com.gini.error.exception.TrainsCoreServerException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestClientErrorDecoder implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        var is4XXError = response.getStatusCode().is4xxClientError();
        var is5XXError = response.getStatusCode().is5xxServerError();

        return is4XXError || is5XXError;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        var is4XXError = response.getStatusCode().is4xxClientError();
        var is5XXError = response.getStatusCode().is5xxServerError();

        if(is4XXError) {
            throw new TrainsCoreClientException("shit happens:D -> 400");


        }

        if(is5XXError) {
            throw new TrainsCoreServerException("big shit happens :D -> 500");
        }

    }
}
