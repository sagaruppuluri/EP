package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Individual validation error
 */

@Schema(name = "ValidationError", description = "Individual validation error")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class ValidationError {

  private @Nullable String field;

  private @Nullable String message;

  private @Nullable String rejectedValue;

  public ValidationError field(@Nullable String field) {
    this.field = field;
    return this;
  }

  /**
   * Field that failed validation
   * @return field
   */
  
  @Schema(name = "field", example = "cgpa", description = "Field that failed validation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("field")
  public @Nullable String getField() {
    return field;
  }

  public void setField(@Nullable String field) {
    this.field = field;
  }

  public ValidationError message(@Nullable String message) {
    this.message = message;
    return this;
  }

  /**
   * Validation error message
   * @return message
   */
  
  @Schema(name = "message", example = "must be between 0.0 and 10.0", description = "Validation error message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public @Nullable String getMessage() {
    return message;
  }

  public void setMessage(@Nullable String message) {
    this.message = message;
  }

  public ValidationError rejectedValue(@Nullable String rejectedValue) {
    this.rejectedValue = rejectedValue;
    return this;
  }

  /**
   * Value that was rejected
   * @return rejectedValue
   */
  
  @Schema(name = "rejectedValue", example = "12.5", description = "Value that was rejected", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rejectedValue")
  public @Nullable String getRejectedValue() {
    return rejectedValue;
  }

  public void setRejectedValue(@Nullable String rejectedValue) {
    this.rejectedValue = rejectedValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationError validationError = (ValidationError) o;
    return Objects.equals(this.field, validationError.field) &&
        Objects.equals(this.message, validationError.message) &&
        Objects.equals(this.rejectedValue, validationError.rejectedValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, message, rejectedValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationError {\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    rejectedValue: ").append(toIndentedString(rejectedValue)).append("\n");
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

