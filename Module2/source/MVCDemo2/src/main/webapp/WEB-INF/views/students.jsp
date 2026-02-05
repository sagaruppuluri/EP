<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 10px;
        }
        .student-count {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
            font-size: 16px;
        }
        .nav-links {
            margin-bottom: 20px;
            display: flex;
            gap: 10px;
        }
        .nav-links a {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .nav-links a:hover {
            background-color: #218838;
        }
        .home-link {
            background-color: #6c757d !important;
        }
        .home-link:hover {
            background-color: #5a6268 !important;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            background-color: #007bff;
            color: white;
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-students {
            text-align: center;
            padding: 40px;
            color: #666;
            font-style: italic;
        }

        .message {
            margin-top: 20px;
            padding: 15px;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 5px;
            text-align: center;
            opacity: 0;
            animation: fadeIn 3s alternate-reverse; /* Fade in and then fade out after 3 seconds */
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
                visibility: hidden;
            }
            100% {
                opacity: 1;
                visibility: visible;
            }
        } 

    </style>
</head>
<body>
    <div class="container">
        <h1>${pageTitle}</h1>
        <div class="student-count">
            Total Students: <strong>${studentCount}</strong>
        </div>

        <div class="nav-links">
            <a href="/" class="home-link">Home</a>
            <a href="/students/add">Add New Student</a>
        </div>

        <!-- Check if students list is empty -->
        <c:choose>
            <c:when test="${empty students}">
                <div class="no-students">
                    No students found. Please add some students.
                </div>
            </c:when>
            <c:otherwise>
                <!-- Display students table -->
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Age</th>
                            <th>Course</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through students using JSTL -->
                        <c:forEach items="${students}" var="student">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.email}</td>
                                <td>${student.age}</td>
                                <td>${student.course}</td>
                                <td>
                                    <a href="/students/edit/${student.id}">Edit</a> |
                                    <a href="/students/delete/${student.id}" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
    </div>
</body>
</html>