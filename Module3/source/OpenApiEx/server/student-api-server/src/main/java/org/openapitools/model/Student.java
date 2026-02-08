package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.openapitools.model.Address;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Complete student record with all fields
 */

@Schema(name = "Student", description = "Complete student record with all fields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class Student {

  private String studentNumber;

  private String name;

  private Address address;

  private Double cgpa;

  private Integer backlogs;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime createdDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime lastModifiedDate;

  public Student() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Student(String studentNumber, String name, Address address, Double cgpa, Integer backlogs) {
    this.studentNumber = studentNumber;
    this.name = name;
    this.address = address;
    this.cgpa = cgpa;
    this.backlogs = backlogs;
  }

  public Student studentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
    return this;
  }

  /**
   * Unique student identifier
   * @return studentNumber
   */
  @NotNull @Pattern(regexp = "^STU[0-9]{3,6}$") 
  @Schema(name = "studentNumber", example = "STU001", description = "Unique student identifier", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("studentNumber")
  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public Student name(String name) {
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

  public Student address(Address address) {
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

  public Student cgpa(Double cgpa) {
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

  public Student backlogs(Integer backlogs) {
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

  public Student createdDate(@Nullable OffsetDateTime createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Date and time when the student record was created
   * @return createdDate
   */
  @Valid 
  @Schema(name = "createdDate", accessMode = Schema.AccessMode.READ_ONLY, example = "2024-01-15T09:00Z", description = "Date and time when the student record was created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdDate")
  public @Nullable OffsetDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(@Nullable OffsetDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public Student lastModifiedDate(@Nullable OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  /**
   * Date and time when the student record was last updated
   * @return lastModifiedDate
   */
  @Valid 
  @Schema(name = "lastModifiedDate", accessMode = Schema.AccessMode.READ_ONLY, example = "2024-02-06T10:30Z", description = "Date and time when the student record was last updated", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastModifiedDate")
  public @Nullable OffsetDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(@Nullable OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(this.studentNumber, student.studentNumber) &&
        Objects.equals(this.name, student.name) &&
        Objects.equals(this.address, student.address) &&
        Objects.equals(this.cgpa, student.cgpa) &&
        Objects.equals(this.backlogs, student.backlogs) &&
        Objects.equals(this.createdDate, student.createdDate) &&
        Objects.equals(this.lastModifiedDate, student.lastModifiedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentNumber, name, address, cgpa, backlogs, createdDate, lastModifiedDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");
    sb.append("    studentNumber: ").append(toIndentedString(studentNumber)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    cgpa: ").append(toIndentedString(cgpa)).append("\n");
    sb.append("    backlogs: ").append(toIndentedString(backlogs)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
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

