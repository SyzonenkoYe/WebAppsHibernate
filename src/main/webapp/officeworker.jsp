<%@ page import="java.time.LocalDate" %>
<%@ page import="cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${titleOfficeWorker} - Web Java 24 (HBN)</title>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .content {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #4a90e2;
            text-align: center;
        }
        label {
            font-weight: bold;
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        button {
            background-color: #4a90e2;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #357ab7;
        }
        a {
            display: inline-block;
            padding: 10px 20px;
            margin-left: 10px;
            background-color: #ccc;
            color: black;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #aaa;
        }
        @media (max-width: 600px) {
            .content {
                padding: 15px;
                box-shadow: none;
            }
            h1 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>
<div class="content">
    <h1><c:out value="${titleOfficeWorker}"/> HBN Proc Java 24</h1>

    <c:if test="${fn:length(errorString) > 0}">
        <p class="error-message"><c:out value="${errorString}"/></p>
    </c:if>

    <form action="officeworker" method="post">
        <input type="hidden" name="id_worker" value="${officeworker.id}" required>

        <div>
            <label for="surname">Surname:</label>
            <input type="text" name="surname" id="surname" value="${officeworker.surname}"
                   pattern="^([A-Z][a-z]+)$" required title="Surname must start with a capital letter and only contain letters."/>
        </div>

        <div>
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="${officeworker.name}"
                   pattern="^([A-Z][a-z]+)$" required title="Name must start with a capital letter and only contain letters."/>
        </div>

        <div>
            <label for="pname">Patronymic name:</label>
            <input type="text" name="pname" id="pname" value="${officeworker.pname}"
                   pattern="^([A-Z][a-z]+)$" required title="Patronymic name must start with a capital letter and only contain letters."/>
        </div>

        <div>
            <label for="startWork">Date of start work:</label>
            <input type="date" name="startWork" id="startWork" value="${officeworker.startWork}"
                   min="${LocalDate.now().minusYears(15)}" max="${LocalDate.now().plusMonths(1)}" required/>
        </div>

        <div>
            <label for="endWork">Date of end work (Optional):</label>
            <input type="date" name="endWork" id="endWork" value="${officeworker.endWork}"
                   min="${LocalDate.now().minusYears(15).minusMonths(4)}"/>
        </div>

        <div>
            <label for="workerCod">Worker Code (4 digits, can start with zero):</label>
            <input type="text" name="workerCod" id="workerCod" value="${officeworker.workerCod}"
                   pattern="^\d{4}$" title="Please enter exactly 4 digits" required maxlength="4" minlength="4"/>
        </div>

        <div>
            <label for="worker_status">Office Worker Status:</label>
            <select id="worker_status" name="officeWorkerStatus" required>
                <c:forEach items="${OfficeWorkerStatus.getOfficeWorkerStatus()}" var="plname">
                    <option value="<c:out value='${plname}'/>"
                        ${officeworker.officeWorkerStatus.getDisplayName() == plname ? 'selected' : ''}>
                        <c:out value="${plname}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div>
            <button type="submit">SAVE</button>
            <a href="<c:out value='${pageContext.request.contextPath}/officeworkers'/>">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>



