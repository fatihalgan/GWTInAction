package org.gwtbook.client.test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class DashboardTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "org.gwtbook.Dashboard";
  }

  /**
   * Add as many tests as you like.
   */
  public void testSimple() {
    assertTrue(true);
  }

}
