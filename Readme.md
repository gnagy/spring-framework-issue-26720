# Example code for triaging Spring Framework Issue 26720

## Overview

Issue logged at https://github.com/spring-projects/spring-framework/issues/26720

Test code: [src/test/java/hu/webhejj/example/SpringFrameworkIssue26720ApplicationTests.java](src/test/java/hu/webhejj/example/SpringFrameworkIssue26720ApplicationTests.java)

Potential fix: [src/test/java/hu/webhejj/example/FixedMockMvcClientHttpRequestFactory.java](src/test/java/hu/webhejj/example/FixedMockMvcClientHttpRequestFactory.java) lines 96-98

The test code calls a simple rest endpoint `/test` that throws a `ResponseStatusException(HttpStatus.BAD_REQUEST, "error")` -- with an error message in the response body.

Expected behavior: that error message can be read as `clientException.getResponseBodyAsString();` after calling `restTemplate.exchange()`

The test fails with:

```
Expected :error
Actual   :

org.opentest4j.AssertionFailedError: expected: <error> but was: <>
    <5 internal lines>
	at hu.webhejj.example.SpringFrameworkIssue26720ApplicationTests.doTest(SpringFrameworkIssue26720ApplicationTests.java:49)
	at hu.webhejj.example.SpringFrameworkIssue26720ApplicationTests.error(SpringFrameworkIssue26720ApplicationTests.java:28)
    ...
```
