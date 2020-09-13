# DesCards – Users

/auth/login
Create session for user and get access token
IN: {username, password}
status codes:
200 - Success
400 - Incorrect format of data
403 - Incorrect login or password
OUT: {token}

/auth/logout
IN: {token}
status codes:
200 - Success
400 - Incorrect format of data
403 - Invalid token
OUT: {}

/auth/validate
IN: {token}
200 - Success
400 - Incorrect format of data
OUT: {is_valid}

/account/register
IN: {username, password}
OUT: {}

/account/delete
IN: {token}
OUT: {}
