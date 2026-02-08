package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.PageInfo;
import org.openapitools.model.Student;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Paginated list of students
 */

@Schema(name = "StudentPageResponse", description = "Paginated list of students")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class StudentPageResponse {

  @Valid
  private List<@Valid Student> content = new ArrayList<>();

  private @Nullable PageInfo page;

  public StudentPageResponse content(List<@Valid Student> content) {
    this.content = content;
    return this;
  }

  public StudentPageResponse addContentItem(Student contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Array of student records
   * @return content
   */
  @Valid 
  @Schema(name = "content", description = "Array of student records", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public List<@Valid Student> getContent() {
    return content;
  }

  public void setContent(List<@Valid Student> content) {
    this.content = content;
  }

  public StudentPageResponse page(@Nullable PageInfo page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
   */
  @Valid 
  @Schema(name = "page", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("page")
  public @Nullable PageInfo getPage() {
    return page;
  }

  public void setPage(@Nullable PageInfo page) {
    this.page = page;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentPageResponse studentPageResponse = (StudentPageResponse) o;
    return Objects.equals(this.content, studentPageResponse.content) &&
        Objects.equals(this.page, studentPageResponse.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentPageResponse {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

