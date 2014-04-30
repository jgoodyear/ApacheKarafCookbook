package com.packt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.frameworkProperty;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.when;
import static org.ops4j.pax.exam.CoreOptions.workingDirectory;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.MavenUtils.asInProject;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFilePut;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.File;

import javax.inject.Inject;

import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;

@ExamReactorStrategy(PerClass.class)
@RunWith(PaxExam.class)
public class TestKaraf {

	@Inject
	protected BundleContext bundleContext;
	
	@Inject
	protected FeaturesService featuresService;

	@Configuration
	public static Option[] configuration() throws Exception {
		return new Option[] {
				karafDistributionConfiguration().frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf")
						.type("zip").versionAsInProject())
						.unpackDirectory(new File("target/paxexam/unpack/"))
						.useDeployFolder(false),
				configureConsole().ignoreLocalConsole(),
				logLevel(LogLevel.INFO),
				keepRuntimeFolder(),
				features(
						maven().groupId("org.apache.karaf.features")
								.artifactId("standard").type("xml")
								.classifier("features").versionAsInProject(),
						"eventadmin")
				};
	}

	@Test
	public void test() {
		assertNotNull(bundleContext);
	}
	
	@Test
	public void feauturesAvailable() throws Exception {
		assertTrue(featuresService.isInstalled(featuresService
				.getFeature("eventadmin")));
	}

}
