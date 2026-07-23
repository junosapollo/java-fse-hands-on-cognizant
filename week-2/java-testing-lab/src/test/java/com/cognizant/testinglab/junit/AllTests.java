package com.cognizant.testinglab.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({BasicAssertionsTest.class, AdvancedJUnitTest.class})
public class AllTests {
}
