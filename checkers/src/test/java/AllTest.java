package es.urjccode.mastercloudapps.adcs.draughts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import controllers.AllControllerTest;
import models.AllModelTest;
import views.AllViewTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    AllModelTest.class, 
    AllControllerTest.class, 
    AllViewTest.class } )
public final class AllTest {
}