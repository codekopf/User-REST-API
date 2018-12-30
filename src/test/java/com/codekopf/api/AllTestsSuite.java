package com.codekopf.api;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({ "**/*Test.class", "!**/Abstract*Test.class" })
public class AllTestsSuite {
}
