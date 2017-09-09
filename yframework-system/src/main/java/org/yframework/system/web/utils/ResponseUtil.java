package org.yframework.system.web.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import java.util.Optional;

public enum ResponseUtil
{
    INSTANCE;

    public <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse)
    {
        return wrapOrNotFound(maybeResponse, (HttpHeaders) null);
    }

    public <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header)
    {
        return (ResponseEntity) maybeResponse.map((response) ->
        {
            return ((BodyBuilder) ResponseEntity.ok().headers(header)).body(response);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}