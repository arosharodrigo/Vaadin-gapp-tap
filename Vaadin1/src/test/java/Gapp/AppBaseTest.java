package Gapp;

//import com.google.appengine.tools.development.ApiProxyLocalImpl;
//import com.google.apphosting.api.ApiProxy;
//import junit.framework.TestCase;

import java.io.File;

/**
 * local service test
 */
public class AppBaseTest {

    /**
     * setup env
     *
     * @throws Exception exception
     */
    // @Override
    public void setUp() throws Exception {
	//  ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
	//  ApiProxy.setDelegate(new ApiProxyLocalImpl(new File(".")) {
		//  });
    }

    /**
     * cleanup
     *
     * @throws Exception exception
     */
    // @Override
    public void tearDown() throws Exception {
	//  ApiProxy.setDelegate(null);
	//  ApiProxy.setEnvironmentForCurrentThread(null);
    }


}