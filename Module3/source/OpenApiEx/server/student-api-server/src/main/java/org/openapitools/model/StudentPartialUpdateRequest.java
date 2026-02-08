package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.Address;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Request body for partial update (all fields optional)
 */

@Schema(name = "StudentPartialUpdateRequest", description = "Request body for partial update (all fields optional)")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class StudentPartialUpdateRequest {

  private @Nullable String name;

  private @Nullable Address address;

  private @Nullable Double cgpa;

  private @Nullable Integer backlogs;

  public StudentPartialUpdateRequest name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @Size(min = 2, max = 100) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public StudentPartialUpdateRequest address(@Nullable Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   */
  @Valid 
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public @Nullable Address getAddress() {
    return address;
  }

  public void setAddress(@Nullable Address address) {
    this.address = address;
  }

  public StudentPartialUpdateRequest cgpa(@Nullable Double cgpa) {
    this.cgpa = cgpa;
    return this;
  }

  /**
   * Get cgpa
   * minimum: 0.0
   * maximum: 10.0
   * @return cgpa
   */
  @DecimalMin(value = "0.0") @DecimalMax(value = "10.0") 
  @Schema(name = "cgpa", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cgpa")
  public @Nullable Double getCgpa() {
    return cgpa;
  }

  public void setCgpa(@Nullable Double cgpa) {
    this.cgpa = cgpa;
  }

  public StudentPartialUpdateRequest backlogs(@Nullable Integer backlogs) {
    this.backlogs = backlogs;
    return this;
  }

  /**
   * Get backlogs
   * minimum: 0
   * @return backlogs
   */
  @Min(value = 0) 
  @Schema(name = "backlogs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("backlogs")
  public @Nullable Integer getBacklogs() {
    return backlogs;
  }

  public void setBacklogs(@Nullable Integer backlogs) {
    this.backlogs = backlogs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentPartialUpdateRequest studentPartialUpdateRequest = (StudentPartialUpdateRequest) o;
    return Objects.equals(this.name, studentPartialUpdateRequest.name) &&
        Objects.equals(this.address, studentPartialUpdateRequest.address) &&
        Objects.equals(this.cgpa, studentPartialUpdateRequest.cgpa) &&
        Objects.equals(this.backlogs, studentPartialUpdateRequest.backlogs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, cgpa, backlogs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentPartialUpdateRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    cgpa: ").append(toIndentedString(cgpa)).append("\n");
    sb.append("    backlogs: ").append(toIndentedString(backlogs)).append("\n");
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

