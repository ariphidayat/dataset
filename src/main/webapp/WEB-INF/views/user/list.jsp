<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
          <c:if test="${not empty msg}">
            <div class="alert alert-primary">${msg}</div>
          </c:if>
          <sec:authorize access="hasRole('ADMIN')">
            <div class="mb-3">
              <a href="/users/new" class="btn btn-md btn-outline-primary" >New User</a>
            </div>
          </sec:authorize>
          <table class="table table-striped">
            <thead>
              <th>Username</th>
              <th>Role</th>
              <th>Action</th>
            </thead>
            </tbody>
              <c:forEach var="user" items="${users}">
                <tr>
                  <td>${user.username}</td>
                  <td>
                    <c:choose>
                      <c:when test="${user.role eq 'ROLE_ADMIN'}">Admin</c:when>
                      <c:when test="${user.role eq 'ROLE_ANNOTATOR'}">Annotator</c:when>
                      <c:otherwise>${user.role}</c:otherwise>
                    </c:choose>
                  </td>
                  <td>
                    <sec:authorize access="hasRole('ADMIN')">
                      <a href="/users/modify?username=${user.username}" class="btn btn-sm btn-outline-primary">Edit</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                      <a href="/users/delete?username=${user.username}" class="btn btn-sm btn-outline-danger">Delete</a>
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