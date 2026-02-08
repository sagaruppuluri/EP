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
 * Advanced search criteria
 */

@Schema(name = "StudentSearchRequest", description = "Advanced search criteria")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class StudentSearchRequest {

  private @Nullable String name;

  private @Nullable String city;

  private @Nullable String state;

  private @Nullable String country;

  private @Nullable Double minCgpa;

  private @Nullable Double maxCgpa;

  private @Nullable Integer maxBacklogs;

  public StudentSearchRequest name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Search by name (partial match)
   * @return name
   */
  
  @Schema(name = "name", description = "Search by name (partial match)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public StudentSearchRequest city(@Nullable String city) {
    this.city = city;
    return this;
  }

  /**
   * Filter by city
   * @return city
   */
  
  @Schema(name = "city", description = "Filter by city", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public @Nullable String getCity() {
    return city;
  }

  public void setCity(@Nullable String city) {
    this.city = city;
  }

  public StudentSearchRequest state(@Nullable String state) {
    this.state = state;
    return this;
  }

  /**
   * Filter by state
   * @return state
   */
  
  @Schema(name = "state", description = "Filter by state", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("state")
  public @Nullable String getState() {
    return state;
  }

  public void setState(@Nullable String state) {
    this.state = state;
  }

  public StudentSearchRequest country(@Nullable String country) {
    this.country = country;
    return this;
  }

  /**
   * Filter by country
   * @return country
   */
  
  @Schema(name = "country", description = "Filter by country", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public @Nullable String getCountry() {
    return country;
  }

  public void setCountry(@Nullable String country) {
    this.country = country;
  }

  public StudentSearchRequest minCgpa(@Nullable Double minCgpa) {
    this.minCgpa = minCgpa;
    return this;
  }

  /**
   * Minimum CGPA
   * minimum: 0.0
   * maximum: 10.0
   * @return minCgpa
   */
  @DecimalMin(value = "0.0") @DecimalMax(value = "10.0") 
  @Schema(name = "minCgpa", description = "Minimum CGPA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minCgpa")
  public @Nullable Double getMinCgpa() {
    return minCgpa;
  }

  public void setMinCgpa(@Nullable Double minCgpa) {
    this.minCgpa = minCgpa;
  }

  public StudentSearchRequest maxCgpa(@Nullable Double maxCgpa) {
    this.maxCgpa = maxCgpa;
    return this;
  }

  /**
   * Maximum CGPA
   * minimum: 0.0
   * maximum: 10.0
   * @return maxCgpa
   */
  @DecimalMin(value = "0.0") @DecimalMax(value = "10.0") 
  @Schema(name = "maxCgpa", description = "Maximum CGPA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxCgpa")
  public @Nullable Double getMaxCgpa() {
    return maxCgpa;
  }

  public void setMaxCgpa(@Nullable Double maxCgpa) {
    this.maxCgpa = maxCgpa;
  }

  public StudentSearchRequest maxBacklogs(@Nullable Integer maxBacklogs) {
    this.maxBacklogs = maxBacklogs;
    return this;
  }

  /**
   * Maximum number of backlogs
   * minimum: 0
   * @return maxBacklogs
   */
  @Min(value = 0) 
  @Schema(name = "maxBacklogs", description = "Maximum number of backlogs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxBacklogs")
  public @Nullable Integer getMaxBacklogs() {
    return maxBacklogs;
  }

  public void setMaxBacklogs(@Nullable Integer maxBacklogs) {
    this.maxBacklogs = maxBacklogs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentSearchRequest studentSearchRequest = (StudentSearchRequest) o;
    return Objects.equals(this.name, studentSearchRequest.name) &&
        Objects.equals(this.city, studentSearchRequest.city) &&
        Objects.equals(this.state, studentSearchRequest.state) &&
        Objects.equals(this.country, studentSearchRequest.country) &&
        Objects.equals(this.minCgpa, studentSearchRequest.minCgpa) &&
        Objects.equals(this.maxCgpa, studentSearchRequest.maxCgpa) &&
        Objects.equals(this.maxBacklogs, studentSearchRequest.maxBacklogs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, city, state, country, minCgpa, maxCgpa, maxBacklogs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentSearchRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    minCgpa: ").append(toIndentedString(minCgpa)).append("\n");
    sb.append("    maxCgpa: ").append(toIndentedString(maxCgpa)).append("\n");
    sb.append("    maxBacklogs: ").append(toIndentedString(maxBacklogs)).append("\n");
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

