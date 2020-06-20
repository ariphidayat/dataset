<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
          <sec:authorize access="hasRole('ADMIN')">
            <form:form action="save" method="post" modelAttribute="user">
              <div class="form-group">
                    <label for="username">Username</label>
                    <form:input path="username" class="form-control"/>
                    <form:errors path="username" class="text-danger"/>
                </div>
                <div>
                    <label for="password">Password</label>
                    <form:input path="password" type="password" class="form-control"/>
                    <form:errors path="password" class="text-danger"/>
                </div>
                <div>
                  <label for="role">Role</label>
                  <form:select path="role" class="form-control">
                    <form:option value="ROLE_ADMIN" label="Admin"/>
                    <form:option value="ROLE_ANNOTATOR" label="Annotator"/>
                  </form:select>
                  <form:errors path="role" class="text-danger"/>
                </div>
                <div class="mt-3">
                  <input type="submit" class="btn btn-outline-primary"></input>
                </div>
              </table>
        </form:form>
          </sec:authorize>
        </div>
      </div>
    </main>
</body>
</html>