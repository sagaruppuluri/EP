package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Statistical information about students
 */

@Schema(name = "StudentStatistics", description = "Statistical information about students")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-08T10:00:46.754234394+05:30[Asia/Kolkata]", comments = "Generator version: 7.19.0")
public class StudentStatistics {

  private @Nullable Long totalStudents;

  private @Nullable Double averageCgpa;

  private @Nullable Long studentsWithNoBacklogs;

  private @Nullable Long studentsWithBacklogs;

  private @Nullable Long topPerformers;

  @Valid
  private Map<String, Integer> cityDistribution = new HashMap<>();

  public StudentStatistics totalStudents(@Nullable Long totalStudents) {
    this.totalStudents = totalStudents;
    return this;
  }

  /**
   * Get totalStudents
   * @return totalStudents
   */
  
  @Schema(name = "totalStudents", example = "150", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalStudents")
  public @Nullable Long getTotalStudents() {
    return totalStudents;
  }

  public void setTotalStudents(@Nullable Long totalStudents) {
    this.totalStudents = totalStudents;
  }

  public StudentStatistics averageCgpa(@Nullable Double averageCgpa) {
    this.averageCgpa = averageCgpa;
    return this;
  }

  /**
   * Get averageCgpa
   * @return averageCgpa
   */
  
  @Schema(name = "averageCgpa", example = "7.85", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("averageCgpa")
  public @Nullable Double getAverageCgpa() {
    return averageCgpa;
  }

  public void setAverageCgpa(@Nullable Double averageCgpa) {
    this.averageCgpa = averageCgpa;
  }

  public StudentStatistics studentsWithNoBacklogs(@Nullable Long studentsWithNoBacklogs) {
    this.studentsWithNoBacklogs = studentsWithNoBacklogs;
    return this;
  }

  /**
   * Get studentsWithNoBacklogs
   * @return studentsWithNoBacklogs
   */
  
  @Schema(name = "studentsWithNoBacklogs", example = "120", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("studentsWithNoBacklogs")
  public @Nullable Long getStudentsWithNoBacklogs() {
    return studentsWithNoBacklogs;
  }

  public void setStudentsWithNoBacklogs(@Nullable Long studentsWithNoBacklogs) {
    this.studentsWithNoBacklogs = studentsWithNoBacklogs;
  }

  public StudentStatistics studentsWithBacklogs(@Nullable Long studentsWithBacklogs) {
    this.studentsWithBacklogs = studentsWithBacklogs;
    return this;
  }

  /**
   * Get studentsWithBacklogs
   * @return studentsWithBacklogs
   */
  
  @Schema(name = "studentsWithBacklogs", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("studentsWithBacklogs")
  public @Nullable Long getStudentsWithBacklogs() {
    return studentsWithBacklogs;
  }

  public void setStudentsWithBacklogs(@Nullable Long studentsWithBacklogs) {
    this.studentsWithBacklogs = studentsWithBacklogs;
  }

  public StudentStatistics topPerformers(@Nullable Long topPerformers) {
    this.topPerformers = topPerformers;
    return this;
  }

  /**
   * Students with CGPA >= 9.0
   * @return topPerformers
   */
  
  @Schema(name = "topPerformers", example = "15", description = "Students with CGPA >= 9.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("topPerformers")
  public @Nullable Long getTopPerformers() {
    return topPerformers;
  }

  public void setTopPerformers(@Nullable Long topPerformers) {
    this.topPerformers = topPerformers;
  }

  public StudentStatistics cityDistribution(Map<String, Integer> cityDistribution) {
    this.cityDistribution = cityDistribution;
    return this;
  }

  public StudentStatistics putCityDistributionItem(String key, Integer cityDistributionItem) {
    if (this.cityDistribution == null) {
      this.cityDistribution = new HashMap<>();
    }
    this.cityDistribution.put(key, cityDistributionItem);
    return this;
  }

  /**
   * Number of students per city
   * @return cityDistribution
   */
  
  @Schema(name = "cityDistribution", example = "{\"Mumbai\":45,\"Delhi\":35}", description = "Number of students per city", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cityDistribution")
  public Map<String, Integer> getCityDistribution() {
    return cityDistribution;
  }

  public void setCityDistribution(Map<String, Integer> cityDistribution) {
    this.cityDistribution = cityDistribution;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentStatistics studentStatistics = (StudentStatistics) o;
    return Objects.equals(this.totalStudents, studentStatistics.totalStudents) &&
        Objects.equals(this.averageCgpa, studentStatistics.averageCgpa) &&
        Objects.equals(this.studentsWithNoBacklogs, studentStatistics.studentsWithNoBacklogs) &&
        Objects.equals(this.studentsWithBacklogs, studentStatistics.studentsWithBacklogs) &&
        Objects.equals(this.topPerformers, studentStatistics.topPerformers) &&
        Objects.equals(this.cityDistribution, studentStatistics.cityDistribution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalStudents, averageCgpa, studentsWithNoBacklogs, studentsWithBacklogs, topPerformers, cityDistribution);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentStatistics {\n");
    sb.append("    totalStudents: ").append(toIndentedString(totalStudents)).append("\n");
    sb.append("    averageCgpa: ").append(toIndentedString(averageCgpa)).append("\n");
    sb.append("    studentsWithNoBacklogs: ").append(toIndentedString(studentsWithNoBacklogs)).append("\n");
    sb.append("    studentsWithBacklogs: ").append(toIndentedString(studentsWithBacklogs)).append("\n");
    sb.append("    topPerformers: ").append(toIndentedString(topPerformers)).append("\n");
    sb.append("    cityDistribution: ").append(toIndentedString(cityDistribution)).append("\n");
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

