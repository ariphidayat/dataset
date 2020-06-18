<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
    <body>
        <div>
            <sec:authorize access="hasRole('ADMIN')">
            <p><a href="/users/new">New User</a></p>
            </sec:authorize>
            <table>
                <th>Username</th>
                <th>Password</th>
                <th>Role</th>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.role}</td>
                        <td>
                            <sec:authorize access="hasRole('ADMIN')">
                            <a href="/users/modify?username=${user.username}">Edit</a>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                            <a href="/users/delete?username=${user.username}">Delete</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>