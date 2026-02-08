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
 * Request body for updating a student (all fields required)
 */

@Schema(name = "StudentUpdateRequest", description = "Request body for updating a student (all fields required)")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class StudentUpdateRequest {

  private String name;

  private Address address;

  private Double cgpa;

  private Integer backlogs;

  public StudentUpdateRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StudentUpdateRequest(String name, Address address, Double cgpa, Integer backlogs) {
    this.name = name;
    this.address = address;
    this.cgpa = cgpa;
    this.backlogs = backlogs;
  }

  public StudentUpdateRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Full name of the student
   * @return name
   */
  @NotNull @Size(min = 2, max = 100) 
  @Schema(name = "name", example = "John Doe", description = "Full name of the student", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StudentUpdateRequest address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   */
  @NotNull @Valid 
  @Schema(name = "address", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public StudentUpdateRequest cgpa(Double cgpa) {
    this.cgpa = cgpa;
    return this;
  }

  /**
   * Cumulative Grade Point Average
   * minimum: 0.0
   * maximum: 10.0
   * @return cgpa
   */
  @NotNull @DecimalMin(value = "0.0") @DecimalMax(value = "10.0") 
  @Schema(name = "cgpa", example = "8.5", description = "Cumulative Grade Point Average", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cgpa")
  public Double getCgpa() {
    return cgpa;
  }

  public void setCgpa(Double cgpa) {
    this.cgpa = cgpa;
  }

  public StudentUpdateRequest backlogs(Integer backlogs) {
    this.backlogs = backlogs;
    return this;
  }

  /**
   * Number of backlog subjects
   * minimum: 0
   * @return backlogs
   */
  @NotNull @Min(value = 0) 
  @Schema(name = "backlogs", example = "0", description = "Number of backlog subjects", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("backlogs")
  public Integer getBacklogs() {
    return backlogs;
  }

  public void setBacklogs(Integer backlogs) {
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
    StudentUpdateRequest studentUpdateRequest = (StudentUpdateRequest) o;
    return Objects.equals(this.name, studentUpdateRequest.name) &&
        Objects.equals(this.address, studentUpdateRequest.address) &&
        Objects.equals(this.cgpa, studentUpdateRequest.cgpa) &&
        Objects.equals(this.backlogs, studentUpdateRequest.backlogs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, cgpa, backlogs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentUpdateRequest {\n");
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

