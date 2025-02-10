<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Web Java 24 - Office Workers (HBN)</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      color: #333;
      margin: 0;
      padding: 0;
    }
    h1 {
      text-align: center;
      color: #4a90e2;
      margin: 20px 0;
    }
    a {
      display: inline-block;
      margin: 10px 0;
      padding: 10px 20px;
      background-color: #4a90e2;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      font-size: 16px;
    }
    a:hover {
      background-color: #357ab7;
    }
    .container {
      width: 90%;
      margin: 0 auto;
      padding: 20px;
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
    }
    th {
      background-color: #4a90e2;
      color: white;
    }
    tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
    input[type="text"], select {
      padding: 8px;
      width: 100%;
      margin-top: 5px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    button, input[type="submit"] {
      background-color: #4a90e2;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover, input[type="submit"]:hover {
      background-color: #357ab7;
    }
    form {
      display: inline;
    }
    .filters {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      margin-bottom: 20px;
    }
    .filters div {
      flex: 1;
      margin: 10px;
    }
    @media (max-width: 768px) {
      .filters div {
        flex-basis: 100%;
      }
      table {
        font-size: 14px;
      }
    }
  </style>
</head>
<body>

<div class="container">
  <a href="start.jsp">To START</a>
  <h1>Office Workers</h1>
  <!-- Фильтрация, сортировка и поиск -->
  <div class="filters">
    <div>
      <b><label for="pl_filter">Filter by status:</label></b>
      <select id="pl_filter" name="pl_filter" onchange="filterByPL()">
        <option value="all">All</option>
        <option value="intern">Intern</option>
        <option value="junior">Junior</option>
        <option value="middle">Middle</option>
        <option value="senior">Senior</option>
        <option value="lead">Lead</option>
        <c:forEach items="${OfficeWorkers.getOfficeWorkers()}" var="plname">
          <option value="<c:out value='${plname}'/>"><c:out value="${plname}"/></option>
        </c:forEach>
      </select>
    </div>

    <div>
      <b>Sort by:</b>
      <br/>
      <button onclick="sortTableByIndex(0)">Surname</button>
      <button onclick="sortTableByIndex(1)">Name</button>
      <button onclick="sortTableByIndex(2)">Patronymic</button>
      <button onclick="sortTableByDate(3)">Start Date</button>
    </div>

    <div>
      <b><label for="search-text">Search by surname:</label></b>
      <input type="text" id="search-text" placeholder="Search..." onkeyup="tableSearch()">
    </div>

    <div>
      <form action="" method="get">
        <button type="submit" accesskey="x">Reset</button>
      </form>
    </div>
  </div>

  <form action="officeworker.jsp" method="GET">
    <input type="submit" value="INSERT OFFICE WORKER"/>
  </form>

  <!-- Таблица сотрудников -->
  <table id="officeworkers-table">
    <tr>
      <th>Surname</th>
      <th>Name</th>
      <th>Patronymic name</th>
      <th>Date of start work</th>
      <th>Date of end work</th>
      <th>Status</th>
      <th>WorkerCode</th>
      <th colspan="2">Actions</th>
    </tr>
    <c:forEach var="e" items="${officeworkers}">
      <tr>
        <td><c:out value="${e.surname}"/></td>
        <td><c:out value="${e.name}"/></td>
        <td><c:out value="${e.pname}"/></td>
        <td>
          <fmt:setLocale value="uk_UA"/>
          <fmt:parseDate value="${e.startWork}" pattern="yyyy-MM-dd" var="e_startWork" type="date"/>
          <fmt:formatDate value="${e_startWork}" pattern="dd-MM-yyyy" />
        </td>
        <td>
          <fmt:setLocale value="uk_UA"/>
          <fmt:parseDate value="${e.endWork}" pattern="yyyy-MM-dd" var="e_endWork" type="date"/>
          <fmt:formatDate value="${e_endWork}" pattern="dd-MM-yyyy" />
        </td>
        <td><c:out value="${e.officeWorkerStatus.getDisplayName()}"/></td>
        <td><c:out value="${e.workerCod}"/></td>
        <td>
          <form action="officeworker" method="GET">
            <input type="hidden" name="id_worker" value="${e.id}"/>
            <input type="submit" value="UPDATE"/>
          </form>
        </td>
        <td>
          <form action="officeworkers/delete" method="GET">
            <input type="hidden" name="id_worker" value="${e.id}"/>
            <input type="submit" value="DELETE" onclick="return confirmation()"/>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<!-- Скрипты для фильтрации и сортировки -->
<script type="text/javascript">
  function confirmation() {
    return confirm('Are you sure you want to delete this data?');
  }

  function tableSearch() {
    var phrase = document.getElementById('search-text').value.toLowerCase();
    var table = document.getElementById('officeworkers-table');
    var rows = table.getElementsByTagName('tr');
    for (var i = 1; i < rows.length; i++) {
      var surname = rows[i].getElementsByTagName('td')[0].innerText.toLowerCase();
      rows[i].style.display = surname.includes(phrase) ? '' : 'none';
    }
  }

  function sortTableByIndex(col_index) {
    var table = document.getElementById("officeworkers-table");
    var rows, switching, i, x, y, shouldSwitch;
    switching = true;
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 1); i++) {
        shouldSwitch = false;
        x = rows[i].getElementsByTagName("TD")[col_index].innerHTML.toLowerCase();
        y = rows[i + 1].getElementsByTagName("TD")[col_index].innerHTML.toLowerCase();
        if (x > y) {
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
  }

  function sortTableByDate(col_index) {
    var table = document.getElementById("officeworkers-table");
    var rows, switching, i, x, y, shouldSwitch;
    switching = true;
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 1); i++) {
        shouldSwitch = false;

        // Отримуємо дати з комірок
        x = rows[i].getElementsByTagName("TD")[col_index].innerHTML;
        y = rows[i + 1].getElementsByTagName("TD")[col_index].innerHTML;

        // Розбиваємо дати
        var dateX = x.split('-');
        var dateY = y.split('-');

        // Створюємо об'єкти Date
        var d1 = new Date(dateX[2], dateX[1] - 1, dateX[0]); // YYYY, MM (0-11), DD
        var d2 = new Date(dateY[2], dateY[1] - 1, dateY[0]); // YYYY, MM (0-11), DD

        // Порівнюємо дати
        if (d1 > d2) {
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
  }


  function filterByPL() {
    var filterValue = document.getElementById('pl_filter').value.toLowerCase();
    var table = document.getElementById('officeworkers-table');
    var rows = table.getElementsByTagName('tr');
    for (var i = 1; i < rows.length; i++) {
      var status = rows[i].getElementsByTagName('td')[5].innerText.toLowerCase();
      rows[i].style.display = (filterValue === 'all' || status === filterValue) ? '' : 'none';
    }
  }
</script>

</body>
</html>
