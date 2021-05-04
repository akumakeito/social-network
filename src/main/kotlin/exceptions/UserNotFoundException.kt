package exceptions

import java.lang.RuntimeException

class UserNotFoundException(message : String) : RuntimeException(message) {
}