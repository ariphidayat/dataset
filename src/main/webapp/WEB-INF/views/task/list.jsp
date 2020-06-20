<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html lang="en">
<head>
    <title>Dataset Management Platform</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/static/styles.css">
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <div class="container-fluid">
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav mr-auto mb-2 mb-md-0">
            <li class="nav-item active">
              <a class="nav-link" href="/">Task</a>
            </li>
            <sec:authorize access="hasAnyRole('ADMIN')">
            <li class="nav-item active">
              <a class="nav-link" href="/users">User</a>
            </li>
            </sec:authorize>
          </ul>
          <c:url var="logoutUrl" value="/logout"/>
          <form id="logoutForm" action="${logoutUrl}" method="POST" class="d-flex">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Logout" class="btn btn-outline-danger"/>
          </form>
        </div>
      </div>
    </nav>

    <main class="container">
      <div class="row m-4 py-5 px-3">
        <div class="w-80 mx-auto">
            <sec:authorize access="hasAnyRole('ADMIN')">
              <form:form method="POST" action="/upload" enctype="multipart/form-data" class="input-group mb-4">
                <input type="file" name="file"/>
                <input type="submit" value="Upload"/>
              </form:form>
            </sec:authorize>
            <table class="table table-striped">
              <thead>
                <th>Id</th>
                <th>Task</th>
                <th>Booked By</th>
                <th>Action</th>
              </thead>
              </tbody>
                <c:forEach var="task" items="${tasks}">
                  <tr>
                    <td>${task.taskId}</td>
                    <td>${task.task}</td>
                    <td>${task.bookedBy}</td>
                    <td>
                      <sec:authorize access="hasAnyRole('ADMIN', 'ANNOTATOR')">
                        <c:choose>
                          <c:when test="${pageContext.request.userPrincipal.name eq task.bookedBy}">
                            <a href="/revoke?taskId=${task.taskId}" class="btn btn-sm btn-outline-warning">Revoke</a>
                            <a href="/download?taskId=${task.taskId}" class="btn btn-sm btn-outline-secondary">Download</a>
                          </c:when>
                          <c:otherwise>
                            <c:if test="${empty task.bookedBy}">
                              <a href="/book?taskId=${task.taskId}" class="btn btn-sm btn-outline-primary">Book</a>
                            </c:if>
                            <sec:authorize access="hasRole('ADMIN')">
                              <a href="/download?taskId=${task.taskId}" class="btn btn-sm btn-outline-secondary">Download</a>
                            </sec:authorize>
                          </c:otherwise>
                        </c:choose>
                      </sec:authorize>
                      <sec:authorize access="hasRole('ADMIN')">
                        <a href="/delete?taskId=${task.taskId}" class="btn btn-sm btn-outline-danger">Delete</a>
                      </sec:authorize>
                    </td>
                  </tr>
                 </c:forEach>
              </tbody>
            </table>
        </div>
      </div>
    </main>
</body>
</html>