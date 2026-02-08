package org.openapitools.api;

import org.openapitools.model.Error;
import org.springframework.lang.Nullable;
import org.openapitools.model.Student;
import org.openapitools.model.StudentCreateRequest;
import org.openapitools.model.StudentPageResponse;
import org.openapitools.model.StudentPartialUpdateRequest;
import org.openapitools.model.StudentSearchRequest;
import org.openapitools.model.StudentStatistics;
import org.openapitools.model.StudentUpdateRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
@Controller
@RequestMapping("${openapi.studentManagement.base-path:/v1}")
public class StudentsApiController implements StudentsApi {

    private final NativeWebRequest request;

    @Autowired
    public StudentsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
