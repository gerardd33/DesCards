from cerberus import Validator
import jwt


def validate(dictionary, schema):
    val = Validator(schema)

    if dictionary is None or not val.validate(dictionary):
        return None

    return dictionary


def jwt_decode(token, secret, algorithm):
    try:
        json = jwt.decode(token,
                          secret,
                          algorithms=[algorithm])
    except (jwt.exceptions.InvalidSignatureError,
            jwt.exceptions.DecodeError):
        return None

    return json
