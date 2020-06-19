<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
    <body>
        <div>
            <sec:authorize access="hasAnyRole('ADMIN', 'ANNOTATOR')">
            <p><a href="/tasks/upload">Upload</a></p>
            </sec:authorize>
            <table>
                <th>Task Id</th>
                <th>Task</th>
                <th>Booked By</th>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${task.taskId}</td>
                        <td>${task.task}</td>
                        <td>${task.bookedBy}</td>
                        <td>
                            <sec:authorize access="hasAnyRole('ADMIN', 'ANNOTATOR')">
                                <c:choose>
                                    <c:when test="${pageContext.request.userPrincipal.name eq task.bookedBy}">
                                        <a href="/tasks/revoke?taskId=${task.taskId}">Revoke</a>
                                        <a href="/tasks/download?taskId=${task.taskId}">Download</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/tasks/book?taskId=${task.taskId}">Book</a>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="/tasks/download?taskId=${task.taskId}">Download</a>
                                        </sec:authorize>
                                    </c:otherwise>
                                </c:choose>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <a href="/tasks/delete?taskId=${task.taskId}">Delete</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>