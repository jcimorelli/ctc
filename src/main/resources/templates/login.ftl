<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h1>Login</h1>
<form action="/login" method="post">
    <div>
        <label>Username:
            <input type="text" name="username" required>
        </label>
    </div>
    <div>
        <label>Password:
            <input type="password" name="password" required>
        </label>
    </div>
    <button type="submit">Login</button>
</form>
</body>
</html>
