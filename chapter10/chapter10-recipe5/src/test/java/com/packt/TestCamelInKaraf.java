package com.packt;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.streamBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.util.Filter;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;


@RunWith(PaxExam.class)
public class TestCamelInKaraf extends CamelTestSupport {

	@Inject
	protected FeaturesService featuresService;

	@Inject
	protected BundleContext bundleContext;
	
	@Inject
	@Filter(value="(camel.context.name=blueprintContext)", timeout=10000)
	protected CamelContext testContext;

	@Configuration
	public static Option[] configure() throws Exception {
		return new Option[] {
				karafDistributionConfiguration()
						.frameworkUrl(
								maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("zip")
										.versionAsInProject()).useDeployFolder(false).karafVersion("3.0.1")
						.unpackDirectory(new File("target/paxexam/unpack/")),
	
				logLevel(LogLevel.WARN),
	
				features(
						maven().groupId("org.apache.camel.karaf").artifactId("apache-camel").type("xml")
								.classifier("features").versionAsInProject(), "camel-blueprint", "camel-test"),
	
				keepRuntimeFolder(),
				streamBundle(
						bundle().add(HelloBean.class)
								.add("OSGI-INF/blueprint/blueprint.xml",
										new File("src/main/resources/OSGI-INF/blueprint/blueprint.xml")
												.toURL())
								.set(Constants.BUNDLE_SYMBOLICNAME, "com.packt.camel-test")
								.set(Constants.DYNAMICIMPORT_PACKAGE, "*").build())
						.start() };
	}

	@Override
	public boolean isCreateCamelContextPerClass() {
		// we override this method and return true, to tell Camel test-kit that
		// it should only create CamelContext once (per class), so we will
		// re-use the CamelContext between each test method in this class
		return true;
	}

	@Test
	public void test() throws Exception {
		assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-core")));
		assertTrue(featuresService.isInstalled(featuresService.getFeature("camel-blueprint")));
	
		assertNotNull(testContext);
		
		MockEndpoint mockEndpoint = (MockEndpoint) testContext.getEndpoint("mock:result");
		mockEndpoint.expectedMessageCount(1);
		
		assertMockEndpointsSatisfied(10000l, TimeUnit.MILLISECONDS);
		
	}

}
