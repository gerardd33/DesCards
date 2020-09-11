from cerberus import Validator


def validate(dictionary, schema):
    val = Validator(schema)

    if dictionary is None or not val.validate(dictionary):
        return None

    return dictionary
