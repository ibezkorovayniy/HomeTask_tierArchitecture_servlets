<%--
  Created by IntelliJ IDEA.
  User: illia
  Date: 12/14/17
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>

<form action="/servlet/signUp" method="post">

    <div class="field">
        <label>Enter your name:</label>
        <div class="input"><input type="text" name="name"/></div>
    </div>
    <div class="field">
        <label>Enter your e-mail:</label>
        <div class="input"><input type="email" name="email"/></div>
    </div>
    <div class="field">
        <label>Enter your password:</label>
        <div class="input"><input type="text" name="password"/></div>
    </div>

    <div class="field">
        <label>Re-enter your password:</label>
        <div class="input"><input type="password" name="repassword" /></div>
    </div>

    <div class="submit">
        <button type="submit">Submit</button>
    </div>

</form>
</body>
</html>
