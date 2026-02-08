package com.university.studentapi.util;

import com.university.studentapi.dto.AddressDTO;
import com.university.studentapi.dto.StudentCreateRequest;
import com.university.studentapi.dto.StudentResponse;
import com.university.studentapi.dto.StudentUpdateRequest;
import com.university.studentapi.model.Student;
import org.springframework.stereotype.Component;

/**
 * Mapper utility to convert between DTOs and entities
 */
@Component
public class StudentMapper {

    /**
     * Convert Student entity to StudentResponse DTO
     */
    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }

        return StudentResponse.builder()
                .studentNumber(student.getStudentNumber())
                .name(student.getName())
                .address(toAddressDTO(student.getAddress()))
                .cgpa(student.getCgpa())
                .backlogs(student.getBacklogs())
                .createdDate(student.getCreatedDate())
                .lastModifiedDate(student.getLastModifiedDate())
                .build();
    }

    /**
     * Convert StudentCreateRequest DTO to Student entity
     */
    public Student toEntity(StudentCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Student.builder()
                .studentNumber(request.getStudentNumber())
                .name(request.getName())
                .address(toAddress(request.getAddress()))
                .cgpa(request.getCgpa())
                .backlogs(request.getBacklogs())
                .build();
    }

    /**
     * Update Student entity from StudentUpdateRequest DTO
     */
    public void updateEntity(StudentUpdateRequest request, Student student) {
        if (request == null || student == null) {
            return;
        }

        if (request.getName() != null) {
            student.setName(request.getName());
        }
        if (request.getAddress() != null) {
            student.setAddress(toAddress(request.getAddress()));
        }
        if (request.getCgpa() != null) {
            student.setCgpa(request.getCgpa());
        }
        if (request.getBacklogs() != null) {
            student.setBacklogs(request.getBacklogs());
        }
    }

    /**
     * Convert AddressDTO to Address entity
     */
    private Student.Address toAddress(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        return Student.Address.builder()
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .build();
    }

    /**
     * Convert Address entity to AddressDTO
     */
    private AddressDTO toAddressDTO(Student.Address address) {
        if (address == null) {
            return null;
        }

        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}
