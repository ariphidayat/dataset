<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<body>
    <div>
        <p>Modify / New User</p>
        <form:form action="save" method="post" modelAttribute="user">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:input path="password" type="password"/></td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td>
                        <form:select path="role">
                            <form:option value="ROLE_ADMIN" label="Admin"/>
                            <form:option value="ROLE_ANNOTATOR" label="Annotator"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>